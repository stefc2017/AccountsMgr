package com.stefancouture.accountsmgr.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stefancouture.accountsmgr.R;
import com.stefancouture.accountsmgr.business.exceptions.PasswordAndReEnterPasswordMatchException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.ReEnterPasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameAlreadyExistsException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameRequiredException;
import com.stefancouture.accountsmgr.objects.User;
import com.stefancouture.accountsmgr.business.RegisterLogic;
import com.stefancouture.accountsmgr.persistence.Users;

public class registerPageActivity extends AppCompatActivity {

    private RegisterLogic register_logic;
    private Users user_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        user_db = new Users(this);
        register_logic = new RegisterLogic(user_db);
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
        String username = ((EditText) findViewById(R.id.register_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.register_password)).getText().toString();
        String reentered_password = ((EditText) findViewById(R.id.register_reenter_password)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.register_firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.register_lastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.register_email)).getText().toString();
        boolean successful = true;

        //create user object to send to business logic layer
        try {
            User user = new User(username, password, reentered_password, firstName, lastName, email);
            register_logic.register(user);
        }catch(Exception e){
            if(e instanceof UsernameRequiredException) {
                showNotification(getString(R.string.usernameRequired));
                successful = false;
            }
            else if(e instanceof PasswordRequiredException) {
                showNotification(getString(R.string.passwordRequired));
                successful = false;
            }
            else if(e instanceof ReEnterPasswordRequiredException) {
                showNotification(getString(R.string.reenterPasswordRequired));
                successful = false;
            }
            else if(e instanceof UsernameAlreadyExistsException) {
                showNotification(getString(R.string.usernameExists));
                successful = false;
            }
            else if(e instanceof PasswordAndReEnterPasswordMatchException) {
                showNotification(getString(R.string.passwordsDontMatch));
                successful = false;
            }
        }

        if(successful) {
            showNotification(getString(R.string.registerSuccessful));
            //send back to login page
            Intent intent = new Intent(this, loginPageActivity.class);
            startActivity(intent);
        }
    }

    private void showNotification(String errorMessage){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String text = errorMessage;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
