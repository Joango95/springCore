package com.joango.utils;

import com.joango.exception.InsufficientFoundsException;
import com.joango.model.Event;
import com.joango.model.UserAccount;

public class UserAccountUtils {

    static public void checkIfUserBalanceIsEnough(UserAccount userAccount, Event event){
        if(!(userAccount.getUserBalance() > event.getTicketPrice())) throw new InsufficientFoundsException();
    }
}
