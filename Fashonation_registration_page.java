package com.example.dhruva.fashonation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fashonation_registration_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashonation_registration_page);

        Intent in = getIntent();
        final EditText uname = (EditText)findViewById(R.id.regis_uname);
        final EditText email = (EditText) findViewById(R.id.regis_email);
        final EditText password = (EditText) findViewById(R.id.regis_password);
       // final EditText c_password = (EditText) findViewById(R.id.regis_c_password);

        Button signup = (Button) findViewById(R.id.signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(password.getText().equals(password.getText()))
                {
                    connection_class connection = new connection_class(Fashonation_registration_page.this);
                    connection.fasho_regis_request(uname.getText().toString(),email.getText().toString(), password.getText().toString());
                }
                else
                {
                    Toast.makeText(Fashonation_registration_page.this,"Password and confirm password field don't match!!!!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
