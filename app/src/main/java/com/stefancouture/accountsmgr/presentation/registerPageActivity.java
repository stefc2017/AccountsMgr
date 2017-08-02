package com.stefancouture.accountsmgr.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.stefancouture.accountsmgr.R;
import com.stefancouture.accountsmgr.business.exceptions.PasswordAndReEnterPasswordMatchException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.ReEnterPasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameAlreadyExistsException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameRequiredException;
import com.stefancouture.accountsmgr.objects.User;
import com.stefancouture.accountsmgr.business.UserLogic;
import com.stefancouture.accountsmgr.persistence.Users;

public class registerPageActivity extends AppCompatActivity {

    private UserLogic user_logic;
    private Users user_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        user_db = new Users(this);
        user_logic = new UserLogic(user_db);
    }

    /**********
     *
     * When user presses cancel button on register page, will send user back to login page
     */
    public void cancel(View view){
        Intent intent = new Intent(this, loginPageActivity.class);
        startActivity(intent);
    }

    /**********
     *
     * When user presses register button on register page, will do processing
     * and add account to users database if it is valid and
     * sends user back to login page.
     */
    public void register(View view){
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String reentered_password = ((EditText) findViewById(R.id.reenter_password)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();

        //create user object to send to business logic layer
        try {
            User user = new User(username, password, reentered_password, firstName, lastName, email);
            user_logic.register(user);
        }catch(Exception e){
            if(e instanceof UsernameRequiredException)
                Log.d("hey", "Username field is required");
            else if(e instanceof PasswordRequiredException)
                Log.d("hey", "Password field is required");
            else if(e instanceof ReEnterPasswordRequiredException)
                Log.d("hey", "Re-enter password field is required");
            else if(e instanceof UsernameAlreadyExistsException)
                Log.d("hey", "Username already exists");
            else if(e instanceof PasswordAndReEnterPasswordMatchException)
                Log.d("hey", "Password and Re-entered password do not match");
        }

        //send back to login page
        Intent intent = new Intent(this, loginPageActivity.class);
        startActivity(intent);
    }
}
