package com.example.userms.controler;

import com.example.userms.PasswordGenerator.PasswordGenerator;
import com.example.userms.entity.RoleEnum;
import com.example.userms.entity.User;
import com.example.userms.Playload.Request.AuthentificationRequest;
import com.example.userms.Playload.Request.RegisterRequest;
import com.example.userms.Playload.Response.AuthentificationResponse;
import com.example.userms.repo.RoleRepository;
import com.example.userms.repo.UserRepository;
import com.example.userms.service.AuthenticationService;
import com.example.userms.validation.EmailExistsException;
import com.example.userms.validation.InvalidPasswordException;
import com.example.userms.validation.PasswordValidator;
import com.example.userms.generic.GenericController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class UserControler extends GenericController<User, Long> {

    private final AuthenticationService service;
    private final RoleRepository rolerepo;
    private final UserRepository userrepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private PasswordValidator passwordValidator;


    //to create the admin
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws MessagingException, EmailExistsException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("#########");
        helper.setTo(request.getEmail());
        helper.setSubject("Welcome to Our PetHealthMate web site");
        String htmlMsg = "<html><head><style>" + "body { background-color: #f1f1f1; font-family: Arial, sans-serif; }" + ".container { max-width: 600px; margin: auto; background-color: #fff; padding: 20px; border-radius: 10px; }" + "h1 { color: #007bff; font-size: 28px; }" + "p { font-size: 18px; line-height: 1.5; }" + "</style></head><body>" + "<div class='container'>" + "<h1>Welcome to PetHealthMate Management App!</h1>" + "<p>Hello " + request.getFirstName() + ",</p>" + "<p>Thank you for registering with us. We are thrilled to have you on board and would like to extend a warm welcome to you. Our team is dedicated to providing you with the best pet healthcare management services available, and we are committed to making your experience with us as pleasant as possible.</p>" + "<p>If you have any questions or concerns, please do not hesitate to contact us. We look forward to serving you!</p>" + "<p>Best regards,<br/>The PetHealthMate Management Team</p>" + "</div></body></html>";
        helper.setText(htmlMsg, true);
        javaMailSender.send(message);
        try {

            passwordValidator.validate(request.getPassword());

            return ResponseEntity.ok(service.register(request));

        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");

        } catch (EmailExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }


    @PostMapping("/users/{roleEnum}")
    public ResponseEntity<?> registerUsers(@RequestBody RegisterRequest request,@PathVariable RoleEnum roleEnum) throws MessagingException, EmailExistsException {
       System.out.println(roleEnum);
        if (roleEnum == RoleEnum.adoptant || roleEnum == RoleEnum.owner) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("#########");
        helper.setTo(request.getEmail());
        helper.setSubject("Welcome to PetHealthMate");
        String htmlMsg = "<html><head><style>" + "body { background-color: #f1f1f1; font-family: Arial, sans-serif; }" + ".container { max-width: 600px; margin: auto; background-color: #fff; padding: 20px; border-radius: 10px; }" + "h1 { color: #007bff; font-size: 28px; }" + "p { font-size: 18px; line-height: 1.5; }" + "</style></head><body>" + "<div class='container'>" + "<h1>Welcome to PetHealth Mate App!</h1>" + "<p>Hello " + request.getFirstName() + ",</p>" + "<p>Thank you for registering with us. We are thrilled to have you on board and would like to extend a warm welcome to you. Our team is dedicated to providing you with the best Pet Health and Mate management services available, and we are committed to making your experience with us as pleasant as possible.</p>" + "<p>If you have any questions or concerns, please do not hesitate to contact us. We look forward to serving you!</p>" + "<p>Best regards,<br/>The PetHealthMate Management Team</p>" + "</div></body></html>";
        helper.setText(htmlMsg, true);
        javaMailSender.send(message);
        try {

            passwordValidator.validate(request.getPassword());

            return ResponseEntity.ok(service.createUser(request, roleEnum));

        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");

        } catch (EmailExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Can't add owner or adoptant with role = "+ roleEnum);
        }
    }



    @PostMapping("/personels/{roleEnum}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> createPersonels(@RequestBody RegisterRequest request, @PathVariable RoleEnum roleEnum) throws EmailExistsException, MessagingException {

        if (roleEnum == RoleEnum.personel_clinic || roleEnum == RoleEnum.veterinaire) {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("#########");
            helper.setTo(request.getEmail());
            helper.setSubject("Welcome to PetHealthMate");

            String htmlMsg = "<html><head><style>" + "body { background-color: #f1f1f1; font-family: Arial, sans-serif; }" + ".container { max-width: 600px; margin: auto; background-color: #fff; padding: 20px; border-radius: 10px; }" + "h1 { color: #007bff; font-size: 28px; }" + "p { font-size: 18px; line-height: 1.5; }" + "</style></head><body>" + "<div class='container'>" + "<h1>Welcome to PetHealthMate Management App!</h1>" + "<p>Hello " + request.getFirstName() + " Welcome to our app! Your email is " + request.getEmail() + " and your password is " + request.getPassword() + " ,</p>" + "<p>If you have any questions or concerns, please do not hesitate to contact us. We look forward to serving you!</p>" + "<p>Best regards,<br/>The PetHealthMate Management Team</p>" + "</div></body></html>";
            helper.setText(htmlMsg, true);

            javaMailSender.send(message);
            try {
                AuthentificationResponse user = service.createUser(request, roleEnum);

                return ResponseEntity.ok(user);

            } catch (EmailExistsException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email deja existent");

            }
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Can't add personal with role = "+ roleEnum);
        }

    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthentificationRequest request) throws InvalidPasswordException {

        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (javax.naming.AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Just change your password ");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Your account has been locked");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while authenticating the user");
        }
    }

    @PostMapping("/loginForUsers")
    //@PreAuthorize("hasRole('adoptant') or hasRole('owner')")
    public ResponseEntity<?> loginForUsers(@RequestBody AuthentificationRequest request) throws InvalidPasswordException {

        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (javax.naming.AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Just change your password ");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Your account has been locked");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while authenticating the user");
        }
    }




//    @GetMapping("/current")
//    public User getCurrentUser(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        User userDetails = (User) authentication.getPrincipal();
//        return userrepo.findByEmail(userDetails.getUsername()).orElse(null);
//    }



    @PutMapping("/ResetByEmail")
    public ResponseEntity<?> resetPassword(@RequestParam String email) throws EmailExistsException, MessagingException {
        User user = userrepo.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
        }

        String firstName = user.getFirstname();
        String lastName = user.getLastname();

        try {

            PasswordGenerator passwordGenerator = new PasswordGenerator();
            String generatedPassword = passwordGenerator.generateValidPassword(8, 12, true, true);

            user.setPassword(passwordEncoder.encode(generatedPassword));
            user.setPasswordneedschange(false);
            userrepo.save(user);
            System.out.println(generatedPassword);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("#########");
            helper.setTo(email);
            helper.setSubject("Welcome");

            String htmlMsg = "<html><head><style>" + "body { background-color: #f1f1f1; font-family: Arial, sans-serif; }" + ".container { max-width: 600px; margin: auto; background-color: #fff; padding: 20px; border-radius: 10px; }" + "h1 { color: #007bff; font-size: 28px; }" + "p { font-size: 18px; line-height: 1.5; }" + "</style></head><body>" + "<div class='container'>" + "<p>Hello " + firstName + " " + lastName + " Your email is " + email + " and your new password is " + generatedPassword + ",</p>" + "<p>Best regards,<br/>PetHealthMate Management Team</p>" + "</div></body></html>";
            helper.setText(htmlMsg, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }

        return ResponseEntity.ok().body("Email sent successfully");
    }


    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        User user = userrepo.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordneedschange(false);
        userrepo.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }



    @PutMapping("/users/{userId}/unblock")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> unblockUserAccount(@PathVariable Long userId) {
        service.unblockAccount(userId);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}/edit")
    public ResponseEntity<?> editUserAccount(@PathVariable("id") Long userId, @RequestBody User updatedUser) {
        User user = userrepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID " + userId));

        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        userrepo.save(user);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userrepo.findAll();
    }

}
