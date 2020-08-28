package DBConnection;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestSetup {

    @AfterTest
    public void closeDBconnection()
    {
        JDBCConnection.connectToDB();
    }

}
