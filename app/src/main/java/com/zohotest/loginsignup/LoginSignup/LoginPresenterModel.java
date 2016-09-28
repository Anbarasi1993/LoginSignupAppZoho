package com.zohotest.loginsignup.LoginSignup;

import android.content.Context;
import android.text.TextUtils;

import com.zohotest.loginsignup.DatabaseHelper.Databasehelper;

import java.util.regex.Pattern;

/**
 * Created by USER on 26-09-2016.
 */

public class LoginPresenterModel implements LoginPresenter {

    private LoginView loginView;
    Databasehelper db;


    public LoginPresenterModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void Checklogincredentials(String Email, String Password) {
        boolean error = false;
        if (loginView != null) {

            if (TextUtils.isEmpty(Email)){
                loginView.setEmailError();
                error = true;
            }

            if (TextUtils.isEmpty(Password)){
                loginView.setPasswordError();
                error = true;
            }
            if (!error){
                db = new Databasehelper(loginView.getApplicationContext());
                int sucess =  db.Check_login_account(Email,Password);
                if(sucess == 1) {
                    loginView.onSuccess(Email,Password);
                }
                else
                {
                    loginView.onFailure();
                }

            }
        }



    }

    public void createAccount(String Name, String Email,String password, String MobileNumber)
    {
        boolean error = false;
        if (loginView != null) {

            if (TextUtils.isEmpty(Name)){
                loginView.setRegNameError();
                error = true;
            }
            else
            {
                if(isAlphanumeric(Name))
                {
                    loginView.setRegNameError();
                    error = true;
                }
                else
                {

                }
            }

            if (TextUtils.isEmpty(Email)){
                loginView.setRegEmailError();
                error = true;
            }
            else
            {
                if(!isValidMail(Email))
                {
                    loginView.setRegEmailError();
                    error = true;
                }
                else
                {

                }
            }
            if (TextUtils.isEmpty(password)){
                loginView.setRegPasswordError();
                error = true;
            }
            else
            {
                if(!isPasswordMatches(password))
                {
                    loginView.setRegPasswordError();
                    error = true;
                }
                else
                {

                }
            }
            if (TextUtils.isEmpty(MobileNumber)){
                loginView.setRegContactError();
                error = true;
            }
            else
            {
                if(!isValidPhoneNumber(MobileNumber))
                {
                    loginView.setRegContactError();
                    error = true;
                }
                else
                {

                }
            }
            if (!error){
                db = new Databasehelper(loginView.getApplicationContext());
              int sucess =  db.Create_login_Account(Email,password,Name,MobileNumber);
                if(sucess == 1) {
                    loginView.onRegistrationSuccess();
                }
                else
                {
                    loginView.onRegistrationFailure();
                }

            }
        }
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    private boolean isAlphanumeric(String Name)
    {
        Pattern p = Pattern.compile("[^a-zA-Z0-9@*&]");
        return p.matcher(Name).find();
    }

    private boolean isValidMail(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static final boolean isValidPhoneNumber(String Phone) {
        if (Phone.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(Phone).matches();
        }
    }

    private boolean isPasswordMatches(String Password) {
        String passwordpattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        Pattern p = Pattern.compile(passwordpattern);
        return p.matcher(Password).matches();
    }
}
