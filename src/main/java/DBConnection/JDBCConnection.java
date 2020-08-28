package DBConnection;

import utils.Logs;

import java.sql.*;

public class JDBCConnection {
    private static final String connectionUrl =
            "jdbc:sqlserver://172.31.0.150:1435;"
                    + "database=WorldEvents;"
                    + "user=Karina_test;"
                    + "password=123;"
                    +"encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=60;";

    private static Connection connection = null;
    private static ResultSet result = null;
    private static Statement statement = null;

    public static Connection connectToDB()  {
        Logs.info("Try to establish DB connection...");
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Logs.info("Connection to DB is successful!");
        }
        catch(SQLException e){
            Logs.info("Connection to DB is failed!");
            Logs.error(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null)
        { try {
            connection.close();
            Logs.info("Connection to DB is closed!");
        }
        catch(SQLException e)
        {
            Logs.error(e.getMessage());
        }
        }
    }

    public static ResultSet selectFromDB(String query) {

        try {
            Statement stmt = connectToDB().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
