package com.example.androidapp.doctor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;

import java.util.List;

public class DoctorsListAdapter extends RecyclerView.Adapter<DoctorsListAdapter.CustomViewHolder> {
    Context context;
    List<DoctorsData> list;

    public DoctorsListAdapter(Context context, List<DoctorsData> list){
        this.context=context;
        this.list=list;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView doctorsName;
        TextView doctorsLocation;
        View view;
        public CustomViewHolder(View view){
            super(view);
            this.view=view;
            doctorsName=view.findViewById(R.id.doctorsName);
            doctorsLocation=view.findViewById(R.id.doctorsLocation);

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate=LayoutInflater.from(parent.getContext());
        View view=inflate.inflate(R.layout.doctor_list_custom_layout,parent,false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.doctorsName.setText("Dr. "+list.get(position).getFirstName()+" "+list.get(position).getLastName());
        holder.doctorsLocation.setText(list.get(position).getState()+", "+list.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
