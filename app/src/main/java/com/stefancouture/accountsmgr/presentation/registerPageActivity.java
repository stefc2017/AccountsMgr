package com.stefancouture.accountsmgr.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stefancouture.accountsmgr.R;

public class registerPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }

    /**********
     *
     * When user presses cancel button on register page, will send user back to login page
     */
    public void cancel(View view){
        Intent intent = new Intent(this, loginPageActivity.class);
        startActivity(intent);
    }
}
