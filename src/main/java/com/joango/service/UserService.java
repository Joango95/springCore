package com.joango.service;

import com.joango.model.User;
import com.joango.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long userId) {
        return userRepository.getUserById(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userRepository.getUsersByName(name, pageSize, pageNum);
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public Boolean deleteUser(long userId) {
        return userRepository.deleteUser(userId);
    }

}
