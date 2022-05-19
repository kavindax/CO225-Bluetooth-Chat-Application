package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.id_username_signin);
        password = findViewById(R.id.id_password_signin);
        signIn = findViewById(R.id.id_register_signin);
        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                if(TextUtils.isEmpty(strPassword) || TextUtils.isEmpty(strUsername)){
                    Toast.makeText(LoginActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUsernamePassword = DB.checkUsernamePassword(strUsername,strPassword);
                    if(checkUsernamePassword==true){
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}