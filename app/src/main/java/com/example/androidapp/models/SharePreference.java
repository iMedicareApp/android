package com.example.androidapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SharePreference {
    private Context context;
    private DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
    public SharedPreferences.Editor mEdit;
    public SharedPreferences mPref;

    public SharePreference(Context context){
        this.context=context;
    }

    public void getDetails(){
        mPref = context.getSharedPreferences("FIREBASE_CONTENT",Context.MODE_PRIVATE);

        populateProfile();
    }

    private void populateProfile() {

        mEdit = mPref.edit();
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> iterate=snapshot.child(Database.SIGN_UP_TABLE).getChildren();
                iterate.forEach(a->{
                    SignUpTable table=a.getValue(SignUpTable.class);

                    if(table.getUserId().equals(userId)){
                        mEdit.putString("mFirstName",table.getFirstName());
                        mEdit.putString("mLastName",table.getLastName());
                        Log.i("USER_FOUND",Thread.currentThread().getName());
                    }
                });

                Iterable<DataSnapshot> iterate2= snapshot.child(Database.PROFILE_TABLE).getChildren();
                iterate2.forEach(a->{
                    ProfileTable table= a.getValue(ProfileTable.class);
                    if(table.getUserId().equals(userId)){
                        mEdit.putString("mAddress",table.getAddress());
                        mEdit.putString("mState",table.getState());
                        mEdit.putString("mCountry", table.getCountry().equals("")?"None":table.getCountry());
                        mEdit.putString("mPhone",table.getPhoneNumber());
                        mEdit.putString("mState",table.getState());
                        mEdit.putString("mDoctorType",table.getDoctorType().equals("")?"None":table.getDoctorType());
                        mEdit.apply();

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
