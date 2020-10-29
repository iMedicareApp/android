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
import com.example.androidapp.databinding.FragmentSignUpDialogBinding;
import com.example.androidapp.util.Registration;
import com.google.android.material.button.MaterialButton;

public class SignUpDialog extends DialogFragment {
    MaterialButton mDoctor;
    View mView;
    String tag;
    FragmentSignUpDialogBinding bind;
    public SignUpDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag=getTag();
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
        bind=FragmentSignUpDialogBinding.inflate(inflater,container,false);
        mView=bind.getRoot();
         //mView=inflater.inflate(R.layout.fragment_sign_up_dialog, container, false);
         return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch(tag){
            case "Incomplete Details":
                bind.warning.setText(R.string.incompleteDetails);
                break;
            case "Conflicting passwords":
                bind.warning.setText(R.string.passwordConflict);
                break;
            case "invalid Email":
                bind.warning.setText(R.string.invalidEmail);
                break;
            case "Duplicate Email":
                bind.warning.setText(R.string.duplicateEmail);
                break;
            case "Weak Password":
                bind.warning.setText(R.string.weakPassword);
                break;
        }
    }
}