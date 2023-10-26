package com.example.userms.service;

import com.example.userms.entity.*;
import com.example.userms.Playload.Request.AuthentificationRequest;
import com.example.userms.Playload.Request.RegisterRequest;
import com.example.userms.Playload.Response.AuthentificationResponse;
import com.example.userms.repo.RoleRepository;
import com.example.userms.repo.TokenRepository;
import com.example.userms.repo.UserRepository;
import com.example.userms.Security.JwtService;
import com.example.userms.validation.EmailExistsException;
import com.example.userms.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private static final int MAX_FAILED_LOGIN_ATTEMPTS = 2;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordValidator passwordValidator;

    public AuthentificationResponse register(RegisterRequest request) throws EmailExistsException {
        logger.debug("RegisterRequest object: {}", request.toString());

        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException("Email already exists");
        }
            User u = User.builder()
                    .firstname(request.getFirstName())
                    .lastname(request.getLastName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .birthdate(request.getBirthdate())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(roleRepository.findByRole(RoleEnum.admin))
                    .build();

        u.setPasswordneedschange(false);
        logger.debug("RegisterRequest object: {}", request.toString());

        logger.debug("User object before saving to the database: {}", u);

        User savedUser = repository.save(u);
        logger.debug("User object after saving to the database: {}", savedUser);
        String jwtToken = jwtService.generateToken(u);
        saveUserToken(savedUser, jwtToken);
        return AuthentificationResponse.builder().token(jwtToken).build();

    }


    public AuthentificationResponse authenticate(AuthentificationRequest request) throws Exception {

        User user = repository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (user.getPasswordneedschange()) {
            throw new javax.naming.AuthenticationException("password needs to be changed");
        }


        if (user.getFailedLoginAttempts() >= MAX_FAILED_LOGIN_ATTEMPTS) {
            user.setAccountLocked(true);
            user.setLastLockTime(LocalDateTime.now());
            repository.save(user);
            throw new LockedException("User account is locked");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            repository.save(user);
            throw e;
        }

        user.setFailedLoginAttempts(0);
        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthentificationResponse.builder().token(jwtToken).build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser((user.getId()));
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Transactional
    public void unblockAccount(Long userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!user.isAccountLocked()) {
            throw new IllegalStateException("User account is not locked");
        }
        user.setAccountLocked(false);
        user.setFailedLoginAttempts(0);
        user.setLastLockTime(null);
        userRepository.save(user);
    }

    public AuthentificationResponse createUser(RegisterRequest request, RoleEnum role) throws EmailExistsException {

        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException("Email already exists");
        }
        User user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .birthdate(request.getBirthdate())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleRepository.findByRole(role))
                .build();

        user.setPasswordneedschange(false);


        User savedUser = repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthentificationResponse.builder().token(jwtToken).build();
    }
}
