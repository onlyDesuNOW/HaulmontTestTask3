package com.haulmont.testtask.entities;

public class Doctor extends Person{

    private String speciality;

    public Doctor(long id, String firstName, String lastName, String patronymic, String specialityName) {
        super(id, firstName, lastName, patronymic);
        this.speciality = specialityName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
