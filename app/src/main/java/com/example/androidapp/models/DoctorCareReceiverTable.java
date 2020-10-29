package com.example.androidapp.models;

public class DoctorCareReceiverTable {
    private int id;
    private String careReceiverId; // careReceiver's ID is used as a foreignKey
    private String doctorId; // doctor's ID is used a s a foreignKey
    private String createdAt;
    private String updatedAt;

    public DoctorCareReceiverTable(int id, String careReceiverId, String doctorId, String createdAt, String updatedAt) {
        this.id = id;
        this.careReceiverId = careReceiverId;
        this.doctorId = doctorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCareReceiverId() {
        return careReceiverId;
    }

    public void setCareReceiverId(String careReceiverId) {
        this.careReceiverId = careReceiverId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
