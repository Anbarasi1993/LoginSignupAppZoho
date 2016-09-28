package com.zohotest.loginsignup.LoginSignup;

/**
 * Created by USER on 26-09-2016.
 */

public interface LoginPresenter {

    void Checklogincredentials(String Email, String Password);

    void createAccount(String Name, String Eamil,String password, String MobileNumber);

    void onDestroy();
}
