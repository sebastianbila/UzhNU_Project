package nullproject.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private Connection connection = null;

    public DatabaseHandler() {
        String url = "jdbc:mysql://46.101.195.159:3306/nullQuest";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Database connection successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
