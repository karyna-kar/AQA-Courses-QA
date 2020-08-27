package DBConnection;

import java.sql.*;

public class JDBCConnection {
    private static final String host = "172.31.0.150";
    private static final String url = "jdbc:sqlserver://" + host + ":1435;database=WorldEvents;";
    private static final String user = "Karina_test";
    private static final String password = "123";

    private static final String connectionUrl =
            "jdbc:sqlserver://172.31.0.150:1433;"
                    + "database=WorldEvents;"
                    + "user=Karina_test;"
                    + "password=123;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    private static Connection connection = null;

    public static Connection connectToDB()  {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e){
            System.out.println("Connection to DB failed!"+e.getMessage());
        }
        return connection;
    }

    public static ResultSet selectFromDB(String query) {
        ResultSet result =null;
        try {
            Statement stmt = connectToDB().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
