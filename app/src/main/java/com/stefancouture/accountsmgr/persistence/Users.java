package com.stefancouture.accountsmgr.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Users extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Users.db";
    private static int versionNumber = 1;
    private static final String USERS_TABLE_NAME = "Users";
    public static final String USERS_COLUMN_USERNAME = "username";
    public static final String USERS_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_FIRST_NAME = "first_name";
    public static final String USER_COLUMN_LAST_NAME = "last_name";
    public static final String USER_COLUMN_EMAIL_ADDRESS = "email_address";

    public Users(Context context){
        super(context, DATABASE_NAME, null, versionNumber);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS Users " +
                        "(username text primary key, password text, first_name text," +
                        " last_name text, email_address text)"
        );
    }//end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }//end onUpgrade

}
