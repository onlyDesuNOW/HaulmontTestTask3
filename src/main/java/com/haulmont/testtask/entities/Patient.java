package com.haulmont.testtask.entities;

public class Patient extends Person {

    private String phoneNumber;

    public Patient(long id, String firstName, String lastName, String patronymic, String number) {
        super(id, firstName, lastName, patronymic);
        this.phoneNumber = number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}

