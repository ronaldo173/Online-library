package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Developer on 24.03.2016.
 */
public class DbConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
        return connection;
    }

    public static ResultSet getResultSetByQuery(String query) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection()) {
            resultSet = getConnection().createStatement().executeQuery(query);
        }
        return resultSet;
    }
}
