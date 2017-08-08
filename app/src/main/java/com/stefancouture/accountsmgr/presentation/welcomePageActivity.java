package com.stefancouture.accountsmgr.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.stefancouture.accountsmgr.R;

public class welcomePageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        //dont let the user use the back button
        //they need to use logout button
    }

    public void logout(MenuItem item){
        Intent intent = new Intent(this, loginPageActivity.class);
        startActivity(intent);
    }
}
