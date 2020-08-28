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
    public void SelectRequestEventNameTest() throws SQLException {
        String query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = 1";
        ResultSet resultSet = JDBCConnection.selectFromDB(query);
        resultSet.first();
        Assert.assertEquals(resultSet.getString("EventName"), "Chernobyl");
        JDBCConnection.closeConnection();
    }
}
