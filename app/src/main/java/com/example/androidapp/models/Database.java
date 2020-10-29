package com.example.androidapp.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

import static java.time.LocalDate.*;

public class Database {
    private int signUpId;
    private final DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
    public static final String SIGN_UP_TABLE="SignUpTable";
    public static final String PROFILE_TABLE="ProfileTable";

    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private String mRole;
    private String mCreatedDate;
    private String mUpdatedDate;
    private String mUserId;

    private static Database instance;
    private Database(){

    }
    public static Database getInstance(){
        if(instance==null){
            instance=new Database();
        }
        return instance;
    }
    public void signUpTable(String email, String firstName, String lastName, String password,
                            String role, String userId){
        mEmail=email;
        mFirstName=firstName;
        mLastName=lastName;
        mPassword=password;
        mRole=role;
        mUserId=userId;

        String createDate= now().getDayOfMonth()+"-"+now().getMonthValue()+"-"
                +now().getYear();
        mCreatedDate=createDate;

        String updateDate= now().getDayOfMonth()+"-"+now().getMonthValue()+"-"
                +now().getYear();
        mUpdatedDate=updateDate;

       getChildCount(); // Method for getting the number of entries in SignUpTable

    }

    private void getChildCount() {
        List<Integer>list=new ArrayList<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.hasChild(SIGN_UP_TABLE)){
                     Iterable<DataSnapshot>iterate=snapshot.child(SIGN_UP_TABLE).getChildren();

                     iterate.forEach(a->list.add(Integer.parseInt(a.getKey())));
                     signUpId=Collections.max(list)+1;
                     populateSignUpTable(signUpId);
                 }
                 else{
                     signUpId=1;
                     populateSignUpTable(signUpId);
                 }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void populateSignUpTable(int sign){

        SignUpTable signUp=new SignUpTable(sign, mUserId, mEmail,mFirstName,mLastName,mPassword,mRole,mCreatedDate,mUpdatedDate);
        mDatabase.child(SIGN_UP_TABLE).child(sign+"").setValue(signUp);

        ProfileTable profile=new ProfileTable(sign,mUserId,"","",
                "","",mRole.equals("Doctor"),"",mCreatedDate,mUpdatedDate);
        mDatabase.child(PROFILE_TABLE).child(sign+"").setValue(profile);
    }
}
