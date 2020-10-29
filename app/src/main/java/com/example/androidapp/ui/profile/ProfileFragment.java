package com.example.androidapp.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.example.androidapp.databinding.FragmentProfileBinding;
import com.example.androidapp.models.Database;
import com.example.androidapp.models.ProfileTable;
import com.example.androidapp.models.SignUpTable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;
    private FragmentProfileBinding bind;
    private View mRoot;
    private DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();

    String mFirstName;
    String mLastName;
    String mAddress;
    String mState;
    String mCountry;
    String mPhone;
    String mDoctorType;
    public List<String> mList = new ArrayList<>();
    List<String> mString= new ArrayList<>(Arrays.asList("None","Dentist","Physician","Gynaecologist","Paediatrician","Optician"));
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getContext().getSharedPreferences("FIREBASE_CONTENT",MODE_PRIVATE);
        edit=pref.edit();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        bind=FragmentProfileBinding.inflate(inflater,container,false);
        mRoot = bind.getRoot();

        bind.save.setOnClickListener(a->updateProfile());

        loadCountrySpinner();

        populateProfile();
        return mRoot;
    }

    private void loadCountrySpinner() {

        Locale[] locale=Locale.getAvailableLocales();

        for(Locale local: locale){
            String string=local.getDisplayCountry().trim();

            if(string.length()>0 && !mList.contains(string)){
                mList.add(string);
            }
        }

        Collections.sort(mList);
        mList.add(0,"None");

        ArrayAdapter<String> adapt= new ArrayAdapter<>(getContext(), R.layout.country_spinner_layout,R.id.text, mList);
        bind.countrySpinner.setAdapter(adapt);
        bind.countrySpinner.setSelection(0);
        bind.countrySpinner.setDropDownVerticalOffset(150);

        loadDoctorTypeSpinner();
    }

    //Load DoctorType Spinner
    private void loadDoctorTypeSpinner() {

        ArrayAdapter<String> adapt= new ArrayAdapter<>(getContext(),R.layout.doctor_type_spinner,R.id.doctorType,mString);

        bind.doctorSpinner.setAdapter(adapt);
        bind.doctorSpinner.setSelection(0);
        bind.doctorSpinner.setDropDownVerticalOffset(60);
    }

    //Fetching data for populating the profile from the database
    private void populateProfile() {
     /*String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

     mRef.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             Iterable<DataSnapshot> iterate=snapshot.child(Database.SIGN_UP_TABLE).getChildren();
             iterate.forEach(a->{
                 SignUpTable table=a.getValue(SignUpTable.class);

                 if(table.getUserId().equals(userId)){
                     mFirstName =table.getFirstName();
                     mLastName=table.getLastName();
                     Log.i("USER_FOUND",Thread.currentThread().getName());
                 }
             });

             Iterable<DataSnapshot> iterate2= snapshot.child(Database.PROFILE_TABLE).getChildren();
             iterate2.forEach(a->{
                 ProfileTable table= a.getValue(ProfileTable.class);
                 if(table.getUserId().equals(userId)){
                     mAddress =table.getAddress();
                     mState =table.getState();
                     mCountry=table.getCountry().equals("")?"None":table.getCountry();
                     mPhone =table.getPhoneNumber();
                     mDoctorType =table.getDoctorType().equals("")?"None":table.getDoctorType();
                 }
             });
             profileDisplay();

         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
*/
        profileDisplay();
    }

    //Displaying the profile data fetched from the database
    public void profileDisplay(){

        bind.firstName.setText(pref.getString("mFirstName",""));
        bind.lastName.setText(pref.getString("mLastName",""));
        bind.address.setText(pref.getString("mAddress",""));
        bind.state.setText(pref.getString("mState",""));
        bind.phone.setText(pref.getString("mPhone",""));

        for(String string: mList){
            if(string.equals(pref.getString("mCountry",""))){
                bind.countrySpinner.setSelection(mList.indexOf(string));
            }
        }
        for(String string:mString){
            if(string.equals(pref.getString("mDoctorType",""))){
                bind.doctorSpinner.setSelection(mString.indexOf(string));
            }
        }


       /* bind.firstName.setText(mFirstName);
        bind.lastName.setText(mLastName);
        bind.address.setText(mAddress);
        bind.state.setText(mState);
        bind.phone.setText(mPhone);
        //countrySpinner
        for(String string: mList){
            if(string.equals(mCountry)){
                bind.countrySpinner.setSelection(mList.indexOf(string));
            }
        }
        //doctorType Spinner
        for(String string:mString){
            if(string.equals(mDoctorType)){
            bind.doctorSpinner.setSelection(mString.indexOf(mDoctorType));
            }
        }*/

    }

    // After clicking on 'save' button. Updating the Database.
    public void updateProfile(){
        String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();

     mFirstName=bind.firstName.getText().toString();
     mLastName=bind.lastName.getText().toString();
     mAddress=bind.address.getText().toString();
     mState=bind.state.getText().toString();
     mPhone=bind.phone.getText().toString();
     mCountry=bind.countrySpinner.getSelectedItem().toString();
     mDoctorType=bind.doctorSpinner.getSelectedItem().toString();

     mRef.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
           Iterable<DataSnapshot> iterate=snapshot.child(Database.SIGN_UP_TABLE).getChildren();

           iterate.forEach(a->{
               SignUpTable sign=a.getValue(SignUpTable.class);
               if(sign.getUserId().equals(userId)){
                   mRef.child(Database.SIGN_UP_TABLE).child(a.getKey()).child("firstName").setValue(mFirstName);
                   mRef.child(Database.SIGN_UP_TABLE).child(a.getKey()).child("lastName").setValue(mLastName);
               }
           });

           Iterable<DataSnapshot> iterate2= snapshot.child(Database.PROFILE_TABLE).getChildren();
           iterate2.forEach(a->{
               ProfileTable profile=a.getValue(ProfileTable.class);
               if(profile.getUserId().equals(userId)){
                   mRef.child(Database.PROFILE_TABLE).child(a.getKey()).child("address").setValue(mAddress);
                   mRef.child(Database.PROFILE_TABLE).child(a.getKey()).child("state").setValue(mState);
                   mRef.child(Database.PROFILE_TABLE).child(a.getKey()).child("phoneNumber").setValue(mPhone);
                   mRef.child(Database.PROFILE_TABLE).child(a.getKey()).child("country").setValue(mCountry);
                   mRef.child(Database.PROFILE_TABLE).child(a.getKey()).child("doctorType").setValue(mDoctorType);
               }
           });
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });

        //UPDATING SHARED_PREFERENCE
        edit.putString("mFirstName",bind.firstName.getText().toString());
        edit.putString("mLastName",bind.lastName.getText().toString());
        edit.putString("mAddress",bind.address.getText().toString());
        edit.putString("mState",bind.state.getText().toString());
        edit.putString("mPhone",bind.phone.getText().toString());
        edit.putString("mCountry",bind.countrySpinner.getSelectedItem().toString());
        edit.putString("mDoctorType",bind.doctorSpinner.getSelectedItem().toString());
        edit.apply();
    }



}