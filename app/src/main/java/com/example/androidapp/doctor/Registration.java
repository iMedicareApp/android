package com.example.androidapp.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.androidapp.R;

public class Registration extends AppCompatActivity {
     private Spinner spinner;
     private String[] role;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        role= new String[]{"Care Receiver", "Doctor"};
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapt=new ArrayAdapter<>(this,R.layout.spinner_layout,R.id.spinnerTextView);
       // adapt.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapt.addAll(role);

        spinner.setAdapter(adapt);
        spinner.setSelection(0);
        spinner.setDropDownVerticalOffset(20);

    }
}