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
        String ins_query = "INSERT INTO [WorldEvents].[dbo].[tblCountry]  (CountryName, ContinentID) VALUES ('Belarus', 3)";
        JDBCConnection.InsertIntoDB(ins_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblCountry]";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.last();
        Assert.assertEquals(resultSet.getString("CountryName"), "Belarus");
    }

    @Test (dependsOnMethods = {"InsertCountryTest"})
    public void DeleteCountryTest() throws SQLException {
        String ins_query = "DELETE FROM [WorldEvents].[dbo].[tblCountry] WHERE  [CountryName] = 'Belarus'";
        JDBCConnection.DeleteFromDB(ins_query);

        String sel_query = "SELECT * FROM [WorldEvents].[dbo].[tblCountry] WHERE [CountryName]='Belarus'";
        ResultSet resultSet = JDBCConnection.selectFromDB(sel_query);
        resultSet.last();
        Assert.assertFalse(resultSet.first());
    }

    @Test //(dependsOnMethods = {"InsertCountryTest"})
    public void CallStoredProcedureTest() {
        String preparedSql = "exec [dbo].[uspCountriesEurope] ?,?";
        int result = JDBCConnection.CallSP(preparedSql, 3);
        Assert.assertNotEquals(result, 0);
    }
}
