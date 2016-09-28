package com.zohotest.loginsignup.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.zohotest.loginsignup.Main.MainActivity;
import com.zohotest.loginsignup.Utils.Constants;

import java.util.ArrayList;


/**
 * Created by Brokenglass on 9/6/2016.
 */
public class Databasehelper extends SQLiteOpenHelper {

    Context context;
    public Databasehelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_USER = "CREATE TABLE " + Constants.TABLE_USER + "("
                + Constants.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.USER_NAME + " TEXT,"
                + Constants.USER_EMAIL + " TEXT,"
                + Constants.USER_PHONE + " TEXT,"
                + Constants.USER_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_USER);
    }


    public int Check_login_account(String Email, String Password)
    {
        int cnt=0;
        String countQuery = "SELECT * FROM "+ Constants.TABLE_USER +" WHERE "+ Constants.USER_EMAIL +"='"+Email+"' AND "+ Constants.USER_PASSWORD+"='"+Password+"' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int Check_alreadyregistered_account(String Email)
    {
        int cnt=0;
        String countQuery = "Select * from user_master where "+Constants.USER_EMAIL+"='"+Email+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int Create_login_Account(String Email, String Password, String username, String ContactNumber)
    {
        int cnt = Check_alreadyregistered_account(Email);
        int sucess;

        if(cnt == 0) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constants.USER_NAME, username);
            values.put(Constants.USER_EMAIL, Email);
            values.put(Constants.USER_PHONE, ContactNumber);
            values.put(Constants.USER_PASSWORD, Password);
            db.insert(Constants.TABLE_USER, null, values);
            db.close(); // Closing database connection

            sucess = 1;
            Log.e("Query-Status",""+sucess);
           return sucess;
        }
        else
        {
            return 2;
        }
    }




}
