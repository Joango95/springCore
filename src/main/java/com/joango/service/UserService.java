package com.joango.service;

import com.joango.model.DTO.UserDTO;
import com.joango.model.User;
import com.joango.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional <User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.getUserByName(name);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
         userRepository.deleteById(userId);
    }

}
