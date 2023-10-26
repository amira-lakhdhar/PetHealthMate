package com.example.userms.service;

import com.example.userms.entity.Role;
import com.example.userms.entity.User;
import com.example.userms.generic.IGenericServiceImp;
import com.example.userms.repo.RoleRepository;
import com.example.userms.repo.UserRepository;
import com.example.userms.service.Interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService extends IGenericServiceImp<User,Long> implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;



    public User createNewUser(User user,Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    public void DeleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
