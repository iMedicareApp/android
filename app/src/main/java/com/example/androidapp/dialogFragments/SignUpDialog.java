package com.example.androidapp.dialogFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.androidapp.R;
import com.example.androidapp.doctor.DoctorsRegistration;
import com.google.android.material.button.MaterialButton;

public class SignUpDialog extends DialogFragment {
    MaterialButton mDoctor;
    View mView;

    public SignUpDialog() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mView=inflater.inflate(R.layout.fragment_sign_up_dialog, container, false);
         return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDoctor=mView.findViewById(R.id.doctor);
        mDoctor.setOnClickListener(v->openSignUpForm());
    }

    public void openSignUpForm(){
        getDialog().dismiss();
       Intent intent= new Intent(getContext(), DoctorsRegistration.class);
       startActivity(intent);

    }
}