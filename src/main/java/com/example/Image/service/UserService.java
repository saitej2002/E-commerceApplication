package com.example.Image.service;

import com.example.Image.entity.User;
import com.example.Image.repository.UserRepository;
import com.example.Image.web.dto.UserRegistrationDto;

public interface UserService {
    void saveUser(UserRegistrationDto userRegistrationDto);
    User findByEmail(String email);
}
