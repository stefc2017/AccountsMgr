package com.stefancouture.accountsmgr.business;

import android.util.Log;

import com.stefancouture.accountsmgr.business.exceptions.IncorrectPasswordException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordAndReEnterPasswordMatchException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.ReEnterPasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.UserDoesNotExistException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameAlreadyExistsException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameRequiredException;
import com.stefancouture.accountsmgr.objects.User;
import com.stefancouture.accountsmgr.persistence.Users;

public class LoginLogic {

    private Users user_db;

    public LoginLogic(Users user_db){
        this.user_db = user_db;
    }

    public void login(String username, String password) throws Exception{
        User user;

        //check if username exists in database
        user = user_db.getByUsername(username);

        if(username.trim().length() == 0){
            throw new UsernameRequiredException();
        }
        else if(password.trim().length() == 0){
            throw new PasswordRequiredException();
        }
        else if(user == null){
            throw new UserDoesNotExistException();
        }
        else if(!password.equals(user.getPassword())){ //check if password matches
                throw new IncorrectPasswordException();
        }
    }//end login
}

