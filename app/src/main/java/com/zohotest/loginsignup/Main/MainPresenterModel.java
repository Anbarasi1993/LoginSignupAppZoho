package com.zohotest.loginsignup.Main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohotest.loginsignup.DatabaseHelper.Databasehelper;
import com.zohotest.loginsignup.Utils.Constants;


/**
 * Created by USER on 28-09-2016.
 */

public class MainPresenterModel implements MainPresenter {
    private MainView mainview;
    Databasehelper db;


    public MainPresenterModel(MainView mainview) {
        this.mainview = mainview;
    }

    public void GetLoginDetails(String Email, String Password)
    {

        if (mainview != null) {
            db = new Databasehelper(mainview.getApplicationContext());
            String TextViewName = "";
            String TextViewEmail = "";
            String TextViewMobile = "";

            String selectQuery = "SELECT * FROM "+ Constants.TABLE_USER +" WHERE "+ Constants.USER_EMAIL +"='"+Email+"' AND "+ Constants.USER_PASSWORD+"='"+Password+"' ";
            SQLiteDatabase sdb = db.getWritableDatabase();
            Cursor cursor = sdb.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {

                    TextViewName = cursor.getString(cursor.getColumnIndex(Constants.USER_NAME));
                    TextViewEmail = cursor.getString(cursor.getColumnIndex(Constants.USER_EMAIL));
                    TextViewMobile = cursor.getString(cursor.getColumnIndex(Constants.USER_PHONE));

                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            mainview.Set_Text_Name(TextViewName,TextViewEmail,TextViewMobile);

        }


    }
}
