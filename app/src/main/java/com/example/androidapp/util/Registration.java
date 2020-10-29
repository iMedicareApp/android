package com.example.androidapp.util;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidapp.LogIn;
import com.example.androidapp.R;
import com.example.androidapp.databinding.ActivityRegistrationBinding;
import com.example.androidapp.dialogFragments.SignUpDialog;
import com.example.androidapp.models.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.lang.ref.WeakReference;

public class Registration extends AppCompatActivity {
     private String[] role;
     private ActivityRegistrationBinding bind;
    public SignUpDialog mSignUp= new SignUpDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        role= new String[]{"Care Receiver", "Doctor"};
        ArrayAdapter<String> adapt=new ArrayAdapter<>(this,R.layout.spinner_layout,R.id.spinnerTextView);
        adapt.addAll(role);

        bind.spinner.setAdapter(adapt);
        bind.spinner.setSelection(0);
        bind.spinner.setDropDownVerticalOffset(60);

    }

    public void signUp(View view){
     if(validate()){
         FirebaseAuth.getInstance().createUserWithEmailAndPassword(bind.email.getText().toString(),bind.password.getText().toString())
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){

                           WeakReference<Database> weakRef=new WeakReference<>(Database.getInstance());
                           weakRef.get().signUpTable(bind.email.getText().toString(),bind.firstName.getText().toString(),
                                   bind.lastName.getText().toString(),bind.password.getText().toString(),
                                   bind.spinner.getSelectedItem().toString(),FirebaseAuth.getInstance().getUid());

                           sendVerificationMail();

                          Intent intent =new Intent(Registration.this, LogIn.class);
                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                          startActivity(intent);
                       }
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 if( e instanceof FirebaseAuthWeakPasswordException){
                     mSignUp.show(getSupportFragmentManager(),"Weak Password");
                 }
                 else if( e instanceof FirebaseAuthInvalidCredentialsException){

                     mSignUp.show(getSupportFragmentManager(),"invalid Email");
                 }
                 else if(e instanceof FirebaseAuthUserCollisionException){
                     mSignUp.show(getSupportFragmentManager(),"Duplicate Email");
                 }

                 else{
                     Toast.makeText(Registration.this, e.getMessage(),Toast.LENGTH_LONG).show();
                 }
             }
         });
     }
    }

    //Validation of Registration form
    public boolean validate(){

        String firstName,lastName,email,password,retypePassword;

        firstName=bind.firstName.getText().toString().trim();
        lastName=bind.lastName.getText().toString().trim();
        email=bind.email.getText().toString().trim();
        password=bind.password.getText().toString().trim();
        retypePassword=bind.retypePassword.getText().toString().trim();

        if(firstName.isEmpty()||lastName.isEmpty()||email.isEmpty()||password.isEmpty()||retypePassword.isEmpty()){

            mSignUp.show(getSupportFragmentManager(),"Incomplete Details");
            return false;
        }
        if(!password.equals(retypePassword)){
            mSignUp.show(getSupportFragmentManager(),"Conflicting passwords");
            return false;
        }
        return true;
    }

    //Method for sending verification email to users
    public void sendVerificationMail(){
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(a->
                {if(a.isSuccessful()){
                    Toast.makeText(Registration.this,"Verification Email Sent",Toast.LENGTH_LONG).show();
                    FirebaseAuth.getInstance().signOut();
                }})
                .addOnFailureListener(a->{
                   Toast.makeText(Registration.this,a.getMessage(),Toast.LENGTH_LONG).show();
                });
    }
}