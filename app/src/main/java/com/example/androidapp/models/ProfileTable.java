package com.example.androidapp.models;

public class ProfileTable {
    private int id;
    private String userId;// ID from the SignUpTable is used as a foreign key
    private String country;
    private String state;
    private String phoneNumber;
    private String address;
    private boolean isDoctor;
    private String doctorType;
    private String createdAt;
    private String updatedAt;

    public ProfileTable(){

    }

    public ProfileTable(int id, String userId, String country, String state, String phoneNumber,
                        String address, boolean isDoctor, String doctorType, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isDoctor = isDoctor;
        this.doctorType = doctorType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
