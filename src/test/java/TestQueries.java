import DBConnection.JDBCConnection;
import DBConnection.TestsSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Logs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestQueries extends TestsSetup {
    @Test
    public void enableDBConnectionTest() {
        Assert.assertNotNull(JDBCConnection.connectToDB());
    }

    @Test(dependsOnMethods = {"enableDBConnectionTest"})
    public void SelectEventNameTest() throws SQLException {
        String query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = 1";
        ResultSet resultSet = JDBCConnection.selectFromDB(query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("EventName"), "Chernobyl");
    }

    @Test(dependsOnMethods = {"SelectEventNameTest"})
    public void SelectJoinTest() throws SQLException {
        String query = "Select EventID, EventName, EventDetails, EventDate, CountryName FROM [dbo].[tblEvent] JOIN [dbo].[tblCountry] ON [tblEvent].CountryID = [tblCountry].CountryID WHERE EventID=5";
        ResultSet resultSet = JDBCConnection.selectFromDB(query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("CountryName"), "United Kingdom");
    }

    @Test(dependsOnMethods = {"SelectJoinTest"})
    public void UpdateEventNameTest() throws SQLException {
        int EventID = 2;

        String upd_query = "Update [WorldEvents].[dbo].[tblEvent] set EventName = 'Test' where EventID = ?";
        JDBCConnection.updateInDB(upd_query, EventID);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = ?";
        ResultSet resultSet = JDBCConnection.selectFromDBWithParameters(sel_query, EventID);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("EventName"), "Test");
    }

    @Test(dependsOnMethods = {"UpdateEventNameTest"})
    public void InsertCountryTest() throws SQLException {
        String ins_query = "INSERT INTO [WorldEvents].[dbo].[tblCountry]  (CountryName, ContinentID) VALUES ('Belarus', 3)";
        JDBCConnection.InsertIntoDB(ins_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblCountry]";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.last();
        Assert.assertEquals(resultSet.getString("CountryName"), "Belarus");
    }

    @Test(dependsOnMethods = {"InsertCountryTest"})
    public void DeleteCountryTest() throws SQLException {
        String ins_query = "DELETE FROM [WorldEvents].[dbo].[tblCountry] WHERE  [CountryName] = 'Belarus'";
        JDBCConnection.DeleteFromDB(ins_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblCountry] WHERE [CountryName]='Belarus'";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.last();
        Assert.assertFalse(resultSet.first());
    }

    @Test(dependsOnMethods = {"DeleteCountryTest"})
    public void CallStoredProcedureTest() {
        String preparedSql = "exec [dbo].[uspCountriesEurope] ?,?";
        int result = JDBCConnection.CallSPCountriesEurope(preparedSql, 3);
        Assert.assertNotEquals(result, 0);
    }
}
