package com.stefancouture.accountsmgr.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stefancouture.accountsmgr.objects.User;

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
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS Users " +
                            "(username text primary key, password text, first_name text," +
                            " last_name text, email_address text)"
            );
        }catch(Exception e){
            processSQLError(e);
        }
    }//end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS Users");
            onCreate(db);
        }catch(Exception e){
            processSQLError(e);
        }
    }//end onUpgrade

    public User getByUsername(String username){
        User userResult = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String userQuery = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USERS_COLUMN_USERNAME +
                    " = " + "'" + username + "'";
            cursor = db.rawQuery(userQuery, null);
        }catch(Exception e){
            processSQLError(e);
        }

        if(cursor.moveToFirst()) {
            userResult.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            userResult.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            userResult.setReentered_password(null);
            userResult.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
            userResult.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
            userResult.setEmail_address(cursor.getString(cursor.getColumnIndex("email_address")));
        }
        else{
            userResult = null;
        }
        cursor.close();
        return userResult;
    }

    private String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }

    public boolean insertIntoTable(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = true;

        String insertQuery = "INSERT INTO " + USERS_TABLE_NAME + " VALUES('" +
                user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFirstName()
                + "', '" + user.getLastName() + "', '" + user.getEmail_address() + "')";

            try {
                db.execSQL(insertQuery);
            } catch (Exception e) {
                processSQLError(e);
                success = false;
            }

        return success;
    }
}
