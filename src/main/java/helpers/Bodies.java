package helpers;

public class Bodies {
    public static String getRequestTokenBody()
    {
        String client_id = "8723fa1b767933f1fc71";
        String client_secret = "b34bf5221aec33a178ef07b5f449d9ad";

        return "{\n" +
            "    \"client_id\": \""+client_id+"\", \n" +
            "\t  \"client_secret\":\""+client_secret+"\"\n" +
            "}";
    }
}
