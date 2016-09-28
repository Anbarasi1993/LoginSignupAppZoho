package com.zohotest.loginsignup.LoginSignup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zohotest.loginsignup.Main.MainActivity;
import com.zohotest.loginsignup.R;

public class LoginSignupActivity extends Activity implements LoginView, View.OnClickListener {



    private TextView Textview_Zoho_Login;
    private TextView Textview_Zoho_Signup;

    private LinearLayout Layout_Zoho_Login;
    private LinearLayout Layout_Zoho_Signup;

    private EditText EditText_Zoho_Email;
    private EditText EditText_Zoho_Password;

    private EditText EditText_Zoho_RegName;
    private EditText EditText_Zoho_RegEmail;
    private EditText EditText_Zoho_RegPassword;
    private EditText EditText_Zoho_RegConatctNumber;



    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsigup);

        EditText_Zoho_Email = (EditText) findViewById(R.id.EditText_Zoho_Email);
        EditText_Zoho_Password = (EditText) findViewById(R.id.EditText_Zoho_Password);
        findViewById(R.id.Button_Zoho_LoginAction).setOnClickListener(this);



        EditText_Zoho_RegName = (EditText) findViewById(R.id.EditText_Zoho_RegName);
        EditText_Zoho_RegEmail = (EditText) findViewById(R.id.EditText_Zoho_RegEmail);
        EditText_Zoho_RegPassword = (EditText) findViewById(R.id.EditText_Zoho_RegPassword);
        EditText_Zoho_RegConatctNumber = (EditText) findViewById(R.id.EditText_Zoho_RegConatctNumber);

        Textview_Zoho_Login = (TextView)findViewById(R.id.Textview_Zoho_Login);
        Textview_Zoho_Signup = (TextView)findViewById(R.id.Textview_Zoho_Signup);

        Layout_Zoho_Login = (LinearLayout) findViewById(R.id.Layout_Zoho_Login);
        Layout_Zoho_Signup = (LinearLayout)findViewById(R.id.Layout_Zoho_Signup);

        findViewById(R.id.Button_Zoho_LoginAction).setOnClickListener(this);

        findViewById(R.id.Button_Zoho_RegisterAction).setOnClickListener(this);

        Textview_Zoho_Login.setOnClickListener(this);

        Textview_Zoho_Signup.setOnClickListener(this);

        presenter = new LoginPresenterModel(this);

    }

    public void onClick(View v) {
        if(v.getId() == R.id.Button_Zoho_LoginAction)
        {
            presenter.Checklogincredentials(EditText_Zoho_Email.getText().toString(), EditText_Zoho_Password.getText().toString());
        }
        else if(v.getId() == R.id.Button_Zoho_RegisterAction)
        {
            presenter.createAccount(EditText_Zoho_RegName.getText().toString(), EditText_Zoho_RegEmail.getText().toString(),EditText_Zoho_RegPassword.getText().toString(),EditText_Zoho_RegConatctNumber.getText().toString());
        }

        else if(v.getId() == R.id.Textview_Zoho_Login)
        {
            Layout_Zoho_Login.setVisibility(View.VISIBLE);
            Layout_Zoho_Signup.setVisibility(View.GONE);
            Textview_Zoho_Login.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            Textview_Zoho_Login.setAlpha(.5f);
            Textview_Zoho_Signup.setBackgroundColor(getResources().getColor(R.color.white));
            EditText_Zoho_Email.setText("");
            EditText_Zoho_Password.setText("");
            EditText_Zoho_Email.setError(null);
            EditText_Zoho_Password.setError(null);

        }

        else if(v.getId() == R.id.Textview_Zoho_Signup)
        {
            Layout_Zoho_Login.setVisibility(View.GONE);
            Layout_Zoho_Signup.setVisibility(View.VISIBLE);
            Textview_Zoho_Signup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            Textview_Zoho_Signup.setAlpha(.5f);
            Textview_Zoho_Login.setBackgroundColor(getResources().getColor(R.color.white));

            EditText_Zoho_RegName.setText("");
            EditText_Zoho_RegEmail.setText("");
            EditText_Zoho_RegPassword.setText("");
            EditText_Zoho_RegConatctNumber.setText("");
            EditText_Zoho_RegName.setError(null);
            EditText_Zoho_RegEmail.setError(null);
            EditText_Zoho_RegPassword.setError(null);
            EditText_Zoho_RegConatctNumber.setError(null);
        }
        else
        {

        }
    }

    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

   public void setEmailError() {
       EditText_Zoho_Email.setError(getString(R.string.Login_Email_Error));
    }

    public void setPasswordError() {
        EditText_Zoho_Password.setError(getString(R.string.Login_Password_Error));
    }

    public void setRegEmailError() {
        EditText_Zoho_RegEmail.setError(getString(R.string.Register_Email_Error));
    }

    public void setRegPasswordError() {
        EditText_Zoho_RegPassword.setError(getString(R.string.Register_Password_Error));
    }

    public void setRegNameError() {
        EditText_Zoho_RegName.setError(getString(R.string.Register_Name_Error));
    }

    public void setRegContactError() {
        EditText_Zoho_RegConatctNumber.setError(getString(R.string.Register_contact_Number_Error));
    }

    public void onRegistrationFailure() {
        Toast.makeText(getApplicationContext(),"Already Account Registered. Please Login or Create New Account",Toast.LENGTH_LONG).show();
    }

    public void onRegistrationSuccess() {
        Toast.makeText(getApplicationContext(),"Your Account created Sucessfully. Please Login",Toast.LENGTH_LONG).show();
        EditText_Zoho_RegName.setText("");
        EditText_Zoho_RegEmail.setText("");
        EditText_Zoho_RegPassword.setText("");
        EditText_Zoho_RegConatctNumber.setText("");
    }

    public void onFailure()
    {
        Toast.makeText(getApplicationContext(),"Your Login Credentials Invalid.Please Try Again ",Toast.LENGTH_LONG).show();
    }
    public void onSuccess(String Email,String Password) {
        Toast.makeText(getApplicationContext(),"Welcome,You Sucessfully logged In.",Toast.LENGTH_LONG).show();
        EditText_Zoho_Email.setText("");
        EditText_Zoho_Password.setText("");
        Intent main = new Intent(this,MainActivity.class);
        main.putExtra("Email",Email);
        main.putExtra("Password",Password);
        startActivity(main);

    }
}
