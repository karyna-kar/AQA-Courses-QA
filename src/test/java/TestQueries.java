import DBConnection.JDBCConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQueries {
    @Test
    public void enableDBConnectionTest()
    {
       // Assertions.assertNotNull(JDBCConnection.connectToDB());
        Assert.assertNotNull(JDBCConnection.connectToDB());
    }
}
