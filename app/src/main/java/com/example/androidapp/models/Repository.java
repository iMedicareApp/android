package com.example.androidapp.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidapp.DoctorsListActivity;
import com.example.androidapp.LogIn;
import com.example.androidapp.R;
import com.example.androidapp.doctor.DoctorsData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class Repository {

    private static Repository instance=null;
    public ArrayList<DoctorsData>list;
    public Map<String, DoctorsData> map= new HashMap();
    private final DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
    private String mFirstName;
    private String mLastName;
    private String mCountry;
    private String mState;
    private String mAddress;
    private String mPhone;
    private String mUserId;

    private Repository(){ }

    public static Repository getInstance(){
        if(instance==null){
            instance=new Repository();
        }
        return instance;
    }
    public void getDoctorsList(Context context){


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot>iterate=snapshot.child(Database.SIGN_UP_TABLE).getChildren();


                iterate.forEach(a->{
                    SignUpTable sign=a.getValue(SignUpTable.class);
                    if(sign.getRole().equals("Doctor")){
                        mFirstName=sign.getFirstName();
                        mLastName=sign.getLastName();
                        mUserId=sign.getUserId();

                        mCountry=snapshot.child(Database.PROFILE_TABLE).child(a.getKey()).child("country").getValue(String.class);
                        mState=snapshot.child(Database.PROFILE_TABLE).child(a.getKey()).child("state").getValue(String.class);
                        mAddress=snapshot.child(Database.PROFILE_TABLE).child(a.getKey()).child("address").getValue(String.class);
                        mPhone=snapshot.child(Database.PROFILE_TABLE).child(a.getKey()).child("phoneNumber").getValue(String.class);

                        map.put(mUserId,new DoctorsData(mFirstName,mLastName,mCountry,mState,mAddress,mPhone,mUserId));
                    }

                });

                list=new ArrayList<>(map.values());
                Log.i("LIST_FROM_CALLBACK",String.valueOf(list.size()));
                Intent intent=new Intent(context, DoctorsListActivity.class);
                intent.putParcelableArrayListExtra("DoctorsData",list);
                context.startActivity(intent);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
