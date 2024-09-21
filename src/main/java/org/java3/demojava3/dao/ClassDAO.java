package org.java3.demojava3.dao;

import org.java3.demojava3.model.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO extends DataDAO{
    public void insertClass(Class classes) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASSES_SQL)) {
            preparedStatement.setString(1, classes.getName());
            preparedStatement.setBoolean(2, classes.isStatus());
            preparedStatement.executeUpdate();
        }
    }

    public void updateClass(Class classes) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLASSES_SQL)) {
            preparedStatement.setString(1, classes.getName());
            preparedStatement.setBoolean(2, classes.isStatus());
            preparedStatement.setInt(3, classes.getId());
            preparedStatement.executeUpdate();
        }
    }

    public Class selectClass(int id) {
        Class classes = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                classes = new Class(id, name, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public List<Class> selectAllClasses() {
        List<Class> classes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                classes.add(new Class(id, name, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public boolean deleteClass(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLASSES_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
