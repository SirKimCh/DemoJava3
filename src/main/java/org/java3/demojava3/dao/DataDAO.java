package org.java3.demojava3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataDAO {
    public String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=demojava3;" +
            "encrypt=true;trustServerCertificate=true;";
    public String jdbcUsername = "sa";
    public String jdbcPassword = "123456";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername,
                    jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (name, phone, address, major) VALUES "
            + " (?, ?, ?, ?);";
    public static final String SELECT_STUDENT_BY_ID = "select id,name,phone,address,major from students where id =?";
    public static final String SELECT_ALL_STUDENTS = "select * from students";
    public static final String DELETE_STUDENTS_SQL = "delete from students where id = ?;";
    public static final String UPDATE_STUDENTS_SQL = "update students set name = ?,phone= ?, address =?, major = ? where id = ?;";
}
