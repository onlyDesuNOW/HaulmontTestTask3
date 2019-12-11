package com.haulmont.testtask.dbcontrollers;

import com.haulmont.testtask.entities.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorControllerDB extends AbstractControllerDB <Doctor, Long> {

    public DoctorControllerDB() {
        super();
    }

    @Override
    protected void addResultsToList(ResultSet rs, List<Doctor> out) throws SQLException {
        while (rs.next()){

            out.add( new Doctor(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5))
            );
        }
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM doctor");

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    @Override
    public Doctor getEntityById(Long id) {
        try {
            ResultSet rs = sendQuery("SELECT * FROM doctor WHERE id = " + id);

            if (rs.next())
                return new Doctor(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public void update(Doctor entity) {
        try {
            sendQuery("UPDATE doctor SET "
                    + "firstname = '" + entity.getFirstName()
                    + "', lastname = '" + entity.getLastName()
                    + "', patronymic = '" + entity.getPatronymic()
                    + "', speciality = '" + entity.getSpeciality()
                    + "' WHERE id = " + entity.getId()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) {
        try {
            sendQuery("DELETE FROM doctor WHERE id = " + id);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Doctor entity) {
        try {
            sendQuery("INSERT INTO doctor (firstname, lastname, patronymic, speciality)" +
                    " VALUES ('"
                    + entity.getFirstName() + "', '"
                    + entity.getLastName() + "', '"
                    + entity.getPatronymic() + "', '"
                    + entity.getSpeciality() + "')"
            );

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
