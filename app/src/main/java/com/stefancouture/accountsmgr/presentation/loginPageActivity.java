package com.stefancouture.accountsmgr.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stefancouture.accountsmgr.R;
import com.stefancouture.accountsmgr.business.LoginLogic;
import com.stefancouture.accountsmgr.business.exceptions.IncorrectPasswordException;
import com.stefancouture.accountsmgr.business.exceptions.PasswordRequiredException;
import com.stefancouture.accountsmgr.business.exceptions.UserDoesNotExistException;
import com.stefancouture.accountsmgr.business.exceptions.UsernameRequiredException;
import com.stefancouture.accountsmgr.persistence.Users;

public class loginPageActivity extends AppCompatActivity {

    private LoginLogic login_logic;
    private Users user_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        user_db = new Users(this);
        login_logic = new LoginLogic(user_db);
    }

    /**********
     *
     * When user presses register button on login page, will send user to register page
     */
    public void register(View view){
        Intent intent = new Intent(this, registerPageActivity.class);
        startActivity(intent);
    }

    /**********
     *
     * When user presses login button on login page, the user will login to their account
     */
    public void login(View view){
        String username = ((EditText) findViewById(R.id.login_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_password)).getText().toString();
        boolean successful = true;
        try{
            login_logic.login(username, password);
        }catch(Exception e){
            if(e instanceof UsernameRequiredException) {
                showNotification(getString(R.string.usernameRequired));
                successful = false;
            }
            else if(e instanceof PasswordRequiredException) {
                showNotification(getString(R.string.passwordRequired));
                successful = false;
            }
            else if(e instanceof UserDoesNotExistException){
               showNotification(getString(R.string.userDoesNotExist));
                successful = false;
            }
            else if(e instanceof IncorrectPasswordException){
                showNotification(getString(R.string.incorrectPassword));
                successful = false;
            }
        }//end try-catch

        if(successful){
            Intent intent = new Intent(this, welcomePageActivity.class);
            startActivity(intent);
        }
    }//end login

    private void showNotification(String errorMessage){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String text = errorMessage;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
