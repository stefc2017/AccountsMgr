package com.stefancouture.accountsmgr.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stefancouture.accountsmgr.R;


public class welcomePageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    @Override
    public void onBackPressed(){
        //dont let the user use the back button
        //they need to use logout button
    }
}
