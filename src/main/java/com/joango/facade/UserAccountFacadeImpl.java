package com.joango.facade;

import com.joango.model.User;
import com.joango.model.UserAccount;
import com.joango.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountFacadeImpl implements UserAccountFacade {

    @Autowired
    UserAccountService userAccountService;


    @Override
    public Optional<UserAccount> getUserAccountByUserId(Long userId) {
        return userAccountService.getUserAccountByUserId(userId);
    }

    @Override
    public UserAccount getUserAccountById(Long userAccountId) {
        return userAccountService.getUserAccountById(userAccountId);
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountService.createUserAccount(userAccount);
    }

    @Override
    public UserAccount createUserAccount(Integer userBalance, User user) {
        UserAccount newUserAccount = new UserAccount(userBalance, user);
        return userAccountService.createUserAccount(newUserAccount);
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {
        return null;
    }
}
