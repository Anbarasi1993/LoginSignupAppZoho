package com.zohotest.loginsignup.LoginSignup;

import android.content.Context;

/**
 * Created by USER on 26-09-2016.
 */

public interface LoginView {

    void setEmailError();

    void setPasswordError();

    void setRegEmailError();

    void setRegPasswordError();

    void setRegNameError();

    void setRegContactError();

    void onSuccess(String Email,String Password);

    void onFailure();

    void onRegistrationSuccess();

    void onRegistrationFailure();

    Context getApplicationContext();
}
