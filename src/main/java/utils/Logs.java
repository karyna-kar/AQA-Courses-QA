package utils;
import org.apache.log4j.Logger;

public class Logs {

    private static final Logger log = Logger.getLogger(Logs.class);

    public static void error(String message) {
        log.error(message);
    }

    public static void info(String message) {
        log.info(message);
    }

}
