package com.haulmont.testtask.dbcontrollers;

import com.haulmont.testtask.entities.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientControllerDB extends AbstractControllerDB<Patient, Long> {

    public PatientControllerDB(){
        super();
    }

    @Override
    protected void addResultsToList(ResultSet rs, List<Patient> out) throws SQLException {

        while (rs.next()){

            out.add( new Patient(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5))
            );
        }
    }

    public List<Patient> find(String excerpt){

        List<Patient> out = new ArrayList<>();

        try {
            ResultSet rs;
            if (excerpt.isEmpty())
                rs = sendQuery("SELECT * FROM patient");

            else
                rs = sendQuery("SELECT * FROM patient WHERE " +
                        "LOWER(firstname) LIKE LOWER('%" + excerpt +
                        "%') OR LOWER(lastname) LIKE LOWER('%" + excerpt +
                        "%') OR LOWER(patronymic) LIKE LOWER('%" + excerpt + "%')");

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    @Override
    public List<Patient> getAll() {

        List<Patient> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM patient");

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    @Override
    public Patient getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM patient WHERE id = " + id);

            if (rs.next())
                return new Patient(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Patient entity) {

        try {
            sendQuery("UPDATE patient SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', lastname = '" + entity.getLastName()
                    + "', patronymic = '" + entity.getPatronymic()
                    + "', phoneNumber = '" + entity.getPhoneNumber()
                    + "' WHERE id = " + entity.getId()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {

        try {
            sendQuery("DELETE FROM patient WHERE id = " + id);

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Patient entity) {

        try {
            sendQuery("INSERT INTO patient (firstname, lastname, patronymic, phonenumber) VALUES ('"
                    + entity.getFirstName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getPatronymic() + "', '"
                    + entity.getPhoneNumber() + "')"
            );

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
