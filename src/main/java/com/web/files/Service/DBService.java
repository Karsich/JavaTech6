package com.web.files.Service;

import com.web.files.Model.UserProfile;

import java.sql.*;

public class DBService {
    public static boolean isExist(String login, String password) {
        try (Connection connection = getMysqlConnection()) {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void save(UserProfile profile) {
        try (Connection connection = getMysqlConnection()) {
            String query = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, profile.getLogin());
            statement.setString(2, profile.getPass());
            statement.setString(3, profile.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        String url = "jdbc:mysql://localhost:3306/javatech";
        String username = "javatechuser";
        String password = "securepassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }

        return connection;
    }
}
