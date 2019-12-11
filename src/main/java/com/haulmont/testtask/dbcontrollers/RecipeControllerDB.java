package com.haulmont.testtask.dbcontrollers;

import com.haulmont.testtask.entities.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecipeControllerDB extends AbstractControllerDB <Recipe, Long> {

    public RecipeControllerDB() { super(); }

    @Override
    protected void addResultsToList(ResultSet rs, List<Recipe> out) throws SQLException {

        while (rs.next()) {
            out.add(new Recipe(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getDate(5),
                    rs.getDate(6),
                    Recipe.Priority.get(rs.getInt(7)))
            );
        }
    }

    @Override
    public List<Recipe> getAll() {

        List<Recipe> out = new ArrayList<>();
        try {

            ResultSet rs = sendQuery("SELECT * FROM recipe");
            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    @Override
    public Recipe getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM recipe WHERE id = " + id);

            if (rs.next())
                return new Recipe(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        Recipe.Priority.get(rs.getInt(7))
                );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @Override
    public void update(Recipe entity) {

        try {

            sendQuery("UPDATE recipe SET "
                    + "description = '" + entity.getDescription()
                    + "', idPatient = " + entity.getIdPatient()
                    + ", idDoctor = " + entity.getIdDoctor()
                    + ", creationDate = DATE '" + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate()))
                    + "', validityDate = DATE '" + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getValidityDate()))
                    + "', priority = " + entity.getPriority().ordinal()
                    + " WHERE id = " + entity.getId()
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) {
        try {

            sendQuery("DELETE FROM recipe WHERE id = " + id);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Recipe entity) {

        try {
            sendQuery("INSERT INTO recipe (description, idPatient, idDoctor, creationDate, validityDate, priority) values ('"
                    + entity.getDescription() + "', "
                    + entity.getIdPatient() + ", "
                    + entity.getIdDoctor() + ", DATE '"
                    + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate())) + "', DATE '"
                    + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getValidityDate())) + "', "
                    + entity.getPriority().ordinal() + ")"
            );
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }
    }


    public List<Recipe> filterByDoctor(long doctorID) {

        List<Recipe> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM recipe WHERE idDoctor = " + doctorID);

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

}

