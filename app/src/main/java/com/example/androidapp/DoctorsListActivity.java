package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidapp.databinding.ActivityDoctorsListBinding;
import com.example.androidapp.doctor.DoctorsData;
import com.example.androidapp.doctor.DoctorsListAdapter;

import java.util.ArrayList;

public class DoctorsListActivity extends AppCompatActivity {
    RecyclerView.LayoutManager mLayout;
    ActivityDoctorsListBinding bind;
    RecyclerView mDoctorsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind=ActivityDoctorsListBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        mDoctorsRecyclerView=bind.doctorsRecycler;
        mLayout=new LinearLayoutManager(this);
        mDoctorsRecyclerView.setLayoutManager(mLayout);

        Intent intent= getIntent();

        ArrayList<DoctorsData> list=intent.getParcelableArrayListExtra("DoctorsData");

        DoctorsListAdapter adapt= new DoctorsListAdapter(this,list);
        mDoctorsRecyclerView.setAdapter(adapt);

        Log.i("NEW ACTIVITY",String.valueOf(list.size()));
    }
}