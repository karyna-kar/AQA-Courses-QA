package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String host = "WIN-TRATKJ7E1QN";
    private static final String url = "jdbc:sqlserver://" + host + "/SQLEXPRESS_TEST";
    private static final String user = "Karina_test";
    private static final String password = "123";

    private static Connection connection = null;

    public static Connection connectToDB()  {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e){
            System.out.println("Connection to DB failed!");
        }
        return connection;
    }
}
