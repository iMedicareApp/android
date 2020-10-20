package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidapp.doctor.Registration;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class LogIn extends AppCompatActivity {
    TextInputEditText password;
    TextInputLayout passwordLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_main);

        password=findViewById(R.id.password);
    }
    public void signUp(View view){
        Intent intent= new Intent(this, Registration.class);
        startActivity(intent);
    }
    public void signIn(View view){
      Intent intent= new Intent(this,SignedIn.class);
      startActivity(intent);
    }
    private void forgetPassword(View view){

    }
    private void resendVerification(View view){

    }

}