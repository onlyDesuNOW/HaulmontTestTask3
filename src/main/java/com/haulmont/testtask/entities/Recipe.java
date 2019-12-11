package com.haulmont.testtask.entities;

import java.util.Date;

public class Recipe {


        public enum Priority {
            Normal,
            Cito,
            Statim;

            public String toString() {
                switch (this) {

                    case Normal:
                        return "Normal";
                    case Cito:
                        return "Cito";
                    case Statim:
                        return "Statim";
                }

                return "";
            }

            public static Priority get(int i) {
                if (i >= 0 && i < values().length)
                    return values()[i];
                return null;
            }
        }

        private long id;
        private String description;
        private long idPatient;
        private long idDoctor;
        private Date creationDate;
        private Date validityDate;
        private Priority priority;

    public Recipe(long id, String description, long idPatient, long idDoctor,
                  Date creationDate, Date validity, Priority priority) {
        this.id = id;
        this.description = description;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.creationDate = creationDate;
        this.validityDate = validity;
        this.priority = priority;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
