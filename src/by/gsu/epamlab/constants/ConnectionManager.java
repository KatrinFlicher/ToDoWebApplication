package by.gsu.epamlab.constants;

import java.sql.*;

public class ConnectionManager {
    static{
        try {
            Class.forName(Constants.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER_NAME, Constants.DB_PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement){
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


}
