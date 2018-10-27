package nullproject.database;

import java.sql.*;

public class DatabaseHandler {

    private Connection connection = null;

    private String connectionSQL = "jdbc:mysql://46.101.195.159:3306/nullQuest";
    private String username = "root";
    String password = "null123";

    public Connection getConnection() {
        try {
            System.out.println("Trying connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionSQL, username, password);
            System.out.println("Database connection successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean userRegistration(String email, String password, int score, String time) {
        String sql = "INSERT INTO users (email, password, score, time) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            Connection conn = this.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, score);
            preparedStatement.setString(4, time);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean userLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection conn = this.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
