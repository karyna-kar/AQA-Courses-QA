import DBConnection.JDBCConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestQueries {
    @Test
    public void enableDBConnectionTest()
    {
       // Assertions.assertNotNull(JDBCConnection.connectToDB());
        Assert.assertNotNull(JDBCConnection.connectToDB());
    }
    @Test (dependsOnMethods = {"enableDBConnectionTest"})
    public void SelectRequestEventNameTest() throws SQLException {
        String query = "SELECT * FROM [WorldEvents].[dbo].[tblEvent] where EventID = 1";
        ResultSet rs = JDBCConnection.selectFromDB(query);
        rs.first();
        Assert.assertEquals(rs.getString("EventName"), "Chernobyl");
    }

}
