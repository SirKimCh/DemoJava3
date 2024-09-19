package org.java3.demojava3.dao;

import org.java3.demojava3.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (name, phone, address, major) VALUES "
            + " (?, ?, ?, ?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,name,phone,address,major from students where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from students";
    private static final String DELETE_STUDENTS_SQL = "delete from students where id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "update students set name = ?,phone= ?, address =?, major = ? where id = ?;";

    DataDAO dataDAO = new DataDAO();

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dataDAO.jdbcURL, dataDAO.jdbcUsername,
                    dataDAO.jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENTS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getPhone());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getMajor());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        System.out.println(UPDATE_STUDENTS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getPhone());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getMajor());
            preparedStatement.setInt(5, student.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student selectStudent(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String major = rs.getString("major");
                student = new Student(id, name, phone, address, major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String major = rs.getString("major");
                students.add(new Student(id, name, phone, address, major));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
