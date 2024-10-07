package com.techelevator.model;

import jakarta.validation.constraints.NotBlank;

public class Technician {

    private int id;
    @NotBlank(message="The name field must not be blank.")
    private String fullName;
    @NotBlank(message="The email address field must not be blank.")
    private String emailAddress;

    public Technician() {

    }


    public Technician(int id, String fullName, String emailAddress) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Technician{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
