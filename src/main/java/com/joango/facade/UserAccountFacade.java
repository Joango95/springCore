package com.joango.facade;

import com.joango.model.User;
import com.joango.model.UserAccount;

import java.util.Optional;

public interface UserAccountFacade {

    Optional<UserAccount> getUserAccountByUserId(Long userId);

    UserAccount getUserAccountById(Long userAccountId);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount createUserAccount(Integer userBalance, User user);

    UserAccount updateUserAccount(UserAccount userAccount);
}

