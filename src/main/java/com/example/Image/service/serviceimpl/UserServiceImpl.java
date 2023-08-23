package com.example.Image.service.serviceimpl;

import com.example.Image.entity.Role;
import com.example.Image.entity.User;
import com.example.Image.repository.RoleRepository;
import com.example.Image.repository.UserRepository;
import com.example.Image.service.UserService;
import com.example.Image.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserRegistrationDto userRegistrationDto) {
          User user = new User();
          user.setName(userRegistrationDto.getName());
          user.setEmail(userRegistrationDto.getEmail());
          user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_CONSUMER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_CONSUMER");
        return roleRepository.save(role);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
