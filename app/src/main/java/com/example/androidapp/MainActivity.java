package com.example.androidapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.androidapp.dialogFragments.SignUpDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextInputEditText password;
    TextInputLayout passwordLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password=findViewById(R.id.password);

    }
    public void signUp(View view){
        SignUpDialog signUp= new SignUpDialog();
        signUp.show(getSupportFragmentManager(),"signUp");
    }
    private void signIn(View view){

    }
    private void forgetPassword(View view){

    }
    private void resendVerification(View view){

    }

}