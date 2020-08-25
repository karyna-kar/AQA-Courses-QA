package helpers;

public class Bodies {
    public static String getRequestTokenBody()
    {
        String client_id = "";
        String client_secret = "";

        return "{\n" +
            "    \"client_id\": \""+client_id+"\", \n" +
            "\t  \"client_secret\":\""+client_secret+"\"\n" +
            "}";
    }
}
