package org.java3.demojava3.dao;

import org.java3.demojava3.model.User;

import java.sql.*;

public class UserDAO extends DataDAO {

    public void registerUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        }
    }

    public User loginUser(String username, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}