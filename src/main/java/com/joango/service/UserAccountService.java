package com.joango.service;

import com.joango.model.UserAccount;
import com.joango.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Optional<UserAccount> getUserAccountByUserId(Long userId){
        return userAccountRepository.getUserAccountByUserId(userId);
    }

    public UserAccount getUserAccountById(Long userAccountId){
        return userAccountRepository.getReferenceById(userAccountId);
    }

    public UserAccount createUserAccount(UserAccount userAccount){
        return userAccountRepository.save(userAccount);
    }

    public UserAccount updateUserAccount(UserAccount userAccount){
        return userAccountRepository.save(userAccount);
    }


}
