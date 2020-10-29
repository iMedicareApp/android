package com.example.androidapp.doctor;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorsData implements Parcelable {
    private String firstName;
    private String lastName;
    private String country;
    private String state;
    private String address;
    private String phone;
    private String userId;

    public DoctorsData(String firstName, String lastName, String country, String state, String address, String phone, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.state = state;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
    }

    protected DoctorsData(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        country = in.readString();
        state = in.readString();
        address = in.readString();
        phone = in.readString();
        userId = in.readString();
    }

    public static final Creator<DoctorsData> CREATOR = new Creator<DoctorsData>() {
        @Override
        public DoctorsData createFromParcel(Parcel in) {
            return new DoctorsData(in);
        }

        @Override
        public DoctorsData[] newArray(int size) {
            return new DoctorsData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(country);
        parcel.writeString(state);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(userId);
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
