package com.joango.repository.userRepository;

import com.joango.model.User;
import com.joango.storage.UserMapStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStorage implements UserRepository {

    @Autowired
    private UserMapStorage userMapStorage;

    private long lastUserId;


    @Override
    public User createUser(User user) {
        lastUserId++;
        user.setId(lastUserId);
        userMapStorage.updateUserData();
        return userMapStorage.userMap.put(String.valueOf(lastUserId), user);
    }

    @Override
    public User updateUser(User user) {
        userMapStorage.updateUserData();
        return userMapStorage.userMap.replace(String.valueOf(user.getId()), user);
    }

    @Override
    public User getUserById(long userId) {
        return userMapStorage.userMap.get(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapStorage.userMap.entrySet()
            .stream()
            .map(userMap -> userMap.getValue())
            .filter(user -> user.getEmail() == email)
            .findFirst()
            .get();
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userMapStorage.userMap.entrySet()
            .stream()
            .map(userMap -> userMap.getValue())
            .filter(user -> user.getName() == name)
            .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteUser(long userId) {
        userMapStorage.updateUserData();
        return userMapStorage.userMap.remove(userId) != null;
    }
}
