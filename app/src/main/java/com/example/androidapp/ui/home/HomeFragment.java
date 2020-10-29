package com.example.androidapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.example.androidapp.databinding.FragmentHomeBinding;
import com.example.androidapp.doctor.DoctorsData;
import com.example.androidapp.models.Repository;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding bind;
    private View mView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        bind=FragmentHomeBinding.inflate(inflater,container,false);
        mView=bind.getRoot();

        bind.getDoctor.setOnClickListener(a->{
            Repository.getInstance().getDoctorsList(getContext());

        });



        return mView;
    }
}