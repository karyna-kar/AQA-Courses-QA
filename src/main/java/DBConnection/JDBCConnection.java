package DBConnection;

import utils.Logs;
import utils.Property;

import java.sql.*;

public class JDBCConnection {
    private static final String connectionUrl =
            "jdbc:sqlserver://172.31.0.150:1435;"
                    + "database=WorldEvents;"
                    + "user=" + Property.getProperty("db.login") + ";"
                    + "password=" + Property.getProperty("db.password") + ";"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=60;";

    private static Connection connection = null;
    private static ResultSet result = null;
    private static Statement statement = null;
    private static PreparedStatement prstaement = null;
    private static CallableStatement callstm = null;

    public static Connection connectToDB() {
        Logs.info("Establishing DB connection...");
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Logs.info("DB connection is successful");
        } catch (SQLException e) {
            Logs.info("DB connection is failed");
            Logs.error(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (result != null) {
            try {
                result.close();
                Logs.info("ResultSet is closed");
            } catch (SQLException e) {
                Logs.error(e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
                Logs.info("Statement is closed");
            } catch (SQLException e) {
                Logs.error(e.getMessage());
            }
        }

        if (prstaement != null) {
            try {
                prstaement.close();
                Logs.info("Prepared statement is closed");
            } catch (SQLException e) {
                Logs.error(e.getMessage());
            }
        }

        if (callstm != null) {
            try {
                callstm.close();
                Logs.info("Callable Statement is closed");
            } catch (SQLException e) {
                Logs.error(e.getMessage());
            }
        }

        if (connection != null) {
            try {
                connection.close();
                Logs.info("DB connection is closed");
            } catch (SQLException e) {
                Logs.error(e.getMessage());
            }
        }
    }

    public static ResultSet selectFromDB(String query) {
        try {
            statement = connectToDB().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Logs.info("Executing Select statement:" + query);
            result = statement.executeQuery(query);
            Logs.info("Data is retrieved");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
        return result;
    }

    public static ResultSet selectFromDBWithParameters(String query, int EventID) {
        try {
            prstaement = connectToDB().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            prstaement.setInt(1, EventID);
            Logs.info("Executing Select statement:" + query + "with parameter EventID = " + EventID);
            result = prstaement.executeQuery();
            Logs.info("Data is retrieved");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
        return result;
    }

    public static void updateInDB(String query, int eventID) {
        try {
            prstaement = connectToDB().prepareStatement(query);
            prstaement.setInt(1, eventID);
            Logs.info("Executing Update statement:" + query);
            prstaement.executeUpdate();
            Logs.info("Data is updated");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
    }

    public static void InsertIntoDB(String query) {
        try {
            statement = connectToDB().createStatement();
            Logs.info("Executing Insert statement:" + query);
            statement.executeUpdate(query);
            Logs.info("Data is inserted");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
    }

    public static void DeleteFromDB(String query) {
        try {
            statement = connectToDB().createStatement();
            Logs.info("Executing Delete statement:" + query);
            statement.executeUpdate(query);
            Logs.info("Data is deleted");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
    }

    public static Integer CallSPCountriesEurope(String query, int countryID) {
        int counter = 0;
        try {
            callstm = connectToDB().prepareCall(query);
            //add input parameter
            callstm.setInt(1, countryID);
            Logs.info("Calling SP:" + query + " with input parameter countryID= " + countryID);
            //set output parameter
            callstm.registerOutParameter(2, Types.INTEGER);
            //execute sp
            callstm.execute();
            //get value of output parameter
            counter = callstm.getInt(2);
            Logs.info("The Stored Procedure is called");
        } catch (SQLException e) {
            Logs.error(e.getMessage());
        }
        return counter;
    }
}
