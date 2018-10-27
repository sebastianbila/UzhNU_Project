package nullproject.database;

import java.sql.*;

public class DatabaseHandler {
    private Connection connection = null;

    private static String urlSQL = "jdbc:mysql://46.101.195.159:3306/nullQuest";
    private static String username = "root";
    private static String password = "null123";

    public DatabaseHandler(){
        try {
            System.out.println("Trying connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(urlSQL, username, password);
            System.out.println("Database connection successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Checking auth
    public boolean authenticationСod(String textUser, String cod){
        if(textUser.equals(cod))
            return true;
        else
            return false;
    }

    //Add new user
    public boolean addUser(String email, String password, int score, String time) {
        String sql = "INSERT INTO users (email, password, score, time) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, score);
            preparedStatement.setString(4, time);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    //Check your exists
    public boolean getUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ok");
                return true;

            }
            else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) { }
        }
    }



    public void closeDB(){
        try {
            connection.close();
        } catch (SQLException e) { }
    }

}
