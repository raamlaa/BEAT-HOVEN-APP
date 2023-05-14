package com.example.easytutomusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.Intent;import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class signup_page extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText Editusername, Editpassword, Editconfirmpwd;
    Button Add;
TextView texte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        getSupportActionBar().hide();
        mydb = new DataBaseHelper(this);
        Editusername = (EditText) findViewById(R.id.username);
        Editpassword = (EditText) findViewById(R.id.password);
        Editconfirmpwd = (EditText) findViewById(R.id.confirm_password);
        Add = (Button) findViewById(R.id.button);
        texte = (TextView) findViewById(R.id.text1);
        addData();
        texte.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view) {
                        Intent intent = new Intent(signup_page.this, login_page.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void addData() {
        Add.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(Editusername.getText().toString().equals("")||Editpassword.getText().toString().equals("")||Editconfirmpwd.getText().toString().equals(""))
                            Toast.makeText(signup_page.this, "Please complete all the fields.", Toast.LENGTH_SHORT).show();
                        else{
                            if(Editpassword.getText().toString().equals(Editconfirmpwd.getText().toString())){
                                Boolean checkUserEmail = mydb.checkEmail(Editusername.getText().toString());
                                if(checkUserEmail == false){
                                    Boolean insert = mydb.Insertdata(Editusername.getText().toString(), Editpassword.getText().toString());
                                    if(insert == true){
                                        Toast.makeText(signup_page.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),login_page.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(signup_page.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(signup_page.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(signup_page.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }
}