package DBConnection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logs;

public class TestsSetup {

    @BeforeMethod
    public void startTest() {
        Logs.info("---- Start test ----");
    }

    @AfterMethod
    public void finishTest() {
        JDBCConnection.closeConnection();
        Logs.info("---- Finish test ---");
    }
}
