package com.example.androidapp.models;

public class RequestTable {
    private int id;
    private String careReceiverId; // the careReceiver's ID is used as a foreign key
    private String doctorId;  // the doctors's ID is used as a foreign key
    private String message;
    private String createdAt;
    private String updatedAt;

    public RequestTable(int id, String careReceiverId, String doctorId, String message, String createdAt, String updatedAt) {
        this.id = id;
        this.careReceiverId = careReceiverId;
        this.doctorId = doctorId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
