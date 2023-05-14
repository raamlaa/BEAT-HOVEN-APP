package com.example.easytutomusicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
DataBaseHelper mydb;
    EditText Editusername, Editpassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();
        mydb = new DataBaseHelper(this);
        Editusername = (EditText) findViewById(R.id.username1);
        Editpassword = (EditText) findViewById(R.id.password1);
        login = (Button) findViewById(R.id.button2);
        loginuser();

    }
    public void loginuser()
    {

        login.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View v) {
                        if (Editusername.getText().toString().equals("") || Editpassword.getText().toString().equals(""))
                            Toast.makeText(login_page.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean checkCredentials = mydb.checkEmailPassword(Editusername.getText().toString(), Editpassword.getText().toString());
                            if (checkCredentials == true) {
                                Toast.makeText(login_page.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(login_page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
        );

    }

}

