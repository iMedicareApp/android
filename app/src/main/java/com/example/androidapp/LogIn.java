package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidapp.databinding.LogInMainBinding;
import com.example.androidapp.util.Registration;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    LogInMainBinding bind;
    TextInputEditText password;
    TextInputLayout passwordLayout;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind=LogInMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setUpAuthenticationListener();

        password=findViewById(R.id.password);
    }
    public void signUp(View view){
        Intent intent= new Intent(this, Registration.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }
    public void signIn(View view){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(bind.logInContent.userName.getText().toString(),
                bind.logInContent.password.getText().toString())
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogIn.this, e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }
    private void forgetPassword(View view){

    }
    private void resendVerification(View view){

    }

   private void setUpAuthenticationListener(){
       listener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user=firebaseAuth.getCurrentUser();
               if(user!=null){
               if(user.isEmailVerified()){
                   Intent intent= new Intent(LogIn.this,SignedIn.class);
                   startActivity(intent);
               }
               else{
                   firebaseAuth.signOut();
                   Toast.makeText(LogIn.this,"Email not verified",Toast.LENGTH_LONG).show();
               }
               }
           }
       };
   }

   @Override
   public void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
   }

   @Override
    public void onDestroy(){
        super.onDestroy();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
        //Log.i("ON_DESTROY","Destroy");
   }
}