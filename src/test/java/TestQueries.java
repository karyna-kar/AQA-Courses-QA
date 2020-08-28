import DBConnection.JDBCConnection;
import DBConnection.TestsSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Logs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestQueries extends TestsSetup {
    @Test
    public void enableDBConnectionTest()
    {
        Assert.assertNotNull(JDBCConnection.connectToDB());
    }

    @Test (dependsOnMethods = {"enableDBConnectionTest"})
    public void SelectEventNameTest() throws SQLException {
        String query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = 1";
        ResultSet resultSet = JDBCConnection.selectFromDB(query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("EventName"), "Chernobyl");
    }

    @Test (dependsOnMethods = {"SelectEventNameTest"})
    public void UpdateEventNameTest() throws SQLException {
        String upd_query = "Update [WorldEvents].[dbo].[tblEvent] set EventName = 'Test' where EventID = 2";
        JDBCConnection.updateInDB(upd_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = 2";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("EventName"), "Test");
    }

    @Test //(dependsOnMethods = {"UpdateEventNameTest"})
    public void InsertCountryTest() throws SQLException {
        String ins_query = "INSERT INTO [WorldEvents].[dbo].[tblCountry]  (CountryID, CountryName, ContinentID) VALUES (45, 'Belarus', 3)";
        JDBCConnection.updateInDB(ins_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblCountry] where EventID = 45";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("CountryName"), "Belarus");
    }
}
