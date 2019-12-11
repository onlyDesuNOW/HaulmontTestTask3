package com.haulmont.testtask.dbcontrollers;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public final class JDBCSingleton {

    private Connection connection;
    private static JDBCSingleton jdbc = null;

    private void loadDefault(){

        loadScriptResource("createtables.sql");
        loadScriptResource("fillingtables.sql");
    }

    private JDBCSingleton(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "SA", "");
            loadDefault();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public static JDBCSingleton getInstance(){

        if (jdbc == null) {
            jdbc = new JDBCSingleton();
        }
        return jdbc;
    }


    private void loadScriptResource(String sqlScript) {

        try {

            SqlFile file = new SqlFile(
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource(sqlScript)).getFile()
                    )
            );

            file.setConnection(connection);
            file.execute();

        } catch (SQLException | IOException | SqlToolError e) {
            e.getMessage();
        }
    }

    public Statement createStatement() {
        {
            try {
                if (connection == null)
                    return null;
                else
                    return connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}