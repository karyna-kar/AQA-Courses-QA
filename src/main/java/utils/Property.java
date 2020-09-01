package utils;

import jdk.nashorn.internal.ir.PropertyKey;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
    public static String getProperty(String propKey)  {
        FileInputStream fis;
        Properties property = new Properties();
        String prop = "";

        try {
            fis = new FileInputStream("src/main/resources/login.properties");
            property.load(fis);

         /*   String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            System.out.println(
                    ", LOGIN: " + login
                    + ", PASSWORD: " + password);*/

            prop = property.getProperty(propKey);

        } catch (FileNotFoundException e) {
            Logs.info("Properties file is absent");
            Logs.error(e.getMessage());
        }
        catch (IOException e) {
            Logs.error(e.getMessage());
        }
        return prop;
    }
}
