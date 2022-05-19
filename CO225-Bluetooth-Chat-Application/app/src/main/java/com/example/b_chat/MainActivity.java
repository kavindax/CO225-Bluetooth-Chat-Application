package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Button signUp,signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.id_username_login);
        password = findViewById(R.id.id_password_login);
        confirmPassword = findViewById(R.id.id_confirm_password_login);
        signUp = findViewById(R.id.id_register_login);
        signIn = findViewById(R.id.id_signin_login);
        DB = new DBHelper(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUser = username.getText().toString();
                String strPassword = password.getText().toString();
                String strConfirmPassword = confirmPassword.getText().toString();

                if(TextUtils.isEmpty(strUser) || TextUtils.isEmpty(strPassword) || TextUtils.isEmpty(strConfirmPassword)){
                    Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else {
                    if(strPassword.equals(strConfirmPassword)){
                        Boolean checkUser = DB.checkUsername(strUser);
                        if(checkUser==false){
                            Boolean insert = DB.insertData(strUser,strPassword);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this,"user already exists!!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}