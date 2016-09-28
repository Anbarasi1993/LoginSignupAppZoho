package com.zohotest.loginsignup.Main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;


import com.zohotest.loginsignup.R;

public class MainActivity extends Activity implements MainView {

    private TextView Textview_Zoho_Email;
    private TextView Textview_Zoho_Name;
    private TextView Textview_Zoho_MobileNumber;

    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Textview_Zoho_Email = (TextView)findViewById(R.id.Textview_Zoho_Email);
        Textview_Zoho_Name = (TextView)findViewById(R.id.Textview_Zoho_Name);
        Textview_Zoho_MobileNumber = (TextView)findViewById(R.id.Textview_Zoho_MobileNumber);

        presenter = new MainPresenterModel(this);

        Intent main = getIntent();
        String Email = main.getStringExtra("Email");
        String Password = main.getStringExtra("Password");

        presenter.GetLoginDetails(Email,Password);
    }

    public void Set_Text_Name(String Name, String Email, String Mobile)
    {
        Textview_Zoho_Email.setText(Email);
        Textview_Zoho_Name.setText(Name);
        Textview_Zoho_MobileNumber.setText(Mobile);
    }

}
