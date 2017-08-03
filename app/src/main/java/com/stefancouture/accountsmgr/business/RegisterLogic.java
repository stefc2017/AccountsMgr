package com.stefancouture.accountsmgr.business;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.stefancouture.accountsmgr.business.exceptions.PasswordAndReEnterPasswordMatchException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.ReEnterPasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameAlreadyExistsException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameRequiredException;
import com.stefancouture.accountsmgr.objects.User;
import com.stefancouture.accountsmgr.persistence.Users;

public class RegisterLogic {

    private Users user_db;

    public RegisterLogic(Users user_db){
        this.user_db = user_db;
    }

    public void register(User user) throws Exception{
        boolean duplicateUsername;

        //check if username is already in database
        duplicateUsername = containsUsername(user.getUsername());

        if(user.getUsername().trim().length() == 0)
            throw new UsernameRequiredException();
        else if(duplicateUsername) //if there is already a user with the entered username
            throw new UsernameAlreadyExistsException();
        else if(user.getPassword().trim().length() == 0)
            throw new PasswordRequiredException();
        else if(user.getReentered_password().trim().length() == 0)
            throw new ReEnterPasswordRequiredException();
        else if(user.getPassword().equals(user.getReentered_password())) { //check if password and re-entered password match
            //add to db
            user_db.insertIntoTable(user);
        }
        else if(!user.getPassword().equals(user.getReentered_password())){
            throw new PasswordAndReEnterPasswordMatchException();
        }
    }//end register

    private boolean containsUsername(String username) throws Exception{
        boolean containsUsername = true;

        User user = user_db.getByUsername(username);

        if(user == null){
            containsUsername = false;
        }

        return containsUsername;
    }
}
