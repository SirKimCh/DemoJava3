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

    // SQL queries for CRUD operations of students
    public static final String INSERT_STUDENTS_SQL = "INSERT INTO students (name, phone, address, major, status, class_id) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String SELECT_STUDENT_BY_ID = "SELECT id, name, phone, address, major, status, class_id FROM students WHERE id = ?;";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM students;";
    public static final String DELETE_STUDENTS_SQL = "DELETE FROM students WHERE id = ?;";
    public static final String UPDATE_STUDENTS_SQL = "UPDATE students SET name = ?, phone = ?, address = ?, major = ?, status = ?, class_id = ? WHERE id = ?;";

    // SQL queries for CRUD operations of classes
    public static final String INSERT_CLASSES_SQL = "INSERT INTO classes (name, status) VALUES (?, ?);";
    public static final String SELECT_CLASS_BY_ID = "SELECT id, name, status FROM classes WHERE id = ?;";
    public static final String SELECT_ALL_CLASSES = "SELECT * FROM classes;";
    public static final String DELETE_CLASSES_SQL = "DELETE FROM classes WHERE id = ?;";
    public static final String UPDATE_CLASSES_SQL = "UPDATE classes SET name = ?, status = ? WHERE id = ?;";
}
