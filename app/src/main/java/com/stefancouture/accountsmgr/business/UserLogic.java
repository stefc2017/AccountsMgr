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

public class UserLogic{

    private Users user_db;

    public UserLogic(Users user_db){
        this.user_db = user_db;
    }
    public void register(User user) throws Exception{
        boolean duplicateUsername;

        if(user.getUsername().length() == 0)
            throw new UsernameRequiredException();
        else if(user.getPassword().length() == 0)
            throw new PasswordRequiredException();
        else if(user.getReentered_password().length() == 0)
            throw new ReEnterPasswordRequiredException();
        else { //username, password and re-enter password are not empty
            //check if username is already in database
            duplicateUsername = containsUsername(user.getUsername());

            if (duplicateUsername) { //if there is already a user with the entered username
                throw new UsernameAlreadyExistsException();
            }
            else {
                //check if password and re-entered password match
                if (user.getPassword().equals(user.getReentered_password())) {
                    //add to db
                    user_db.insertIntoTable(user);
                } else {
                    throw new PasswordAndReEnterPasswordMatchException();
                }
            }
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
