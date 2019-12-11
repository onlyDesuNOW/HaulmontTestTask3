package com.haulmont.testtask.entities;

public abstract class Person {

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;

    public Person(long id, String firstName, String lastName, String patronymic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String toString() {
        String name="";
        if (patronymic.length()!=0) name = lastName + " " + firstName.charAt(0)+ ". " + patronymic.charAt(0)+".";
        if (patronymic.length()==0) name = lastName + " " + firstName.charAt(0)+ ". ";
        return name;
    }


    public boolean contains(String excerpt){

        String[] split = excerpt.split(" ");
        for (String s: split) {
            if (firstName.toLowerCase().contains(s.toLowerCase())
                    || lastName.toLowerCase().contains(s.toLowerCase())
                    || patronymic.toLowerCase().contains(s.toLowerCase()))

                return true;
        }
        return false;
    }

}
