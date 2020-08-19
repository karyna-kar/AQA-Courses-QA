package pages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class Parser {


    public static String getToken(Response response)
    {
        String xapppToken = getJsonObjectFromResponse(response).get("token").getAsString();
        return xapppToken;
    }

    public static JsonObject getJsonObjectFromResponse(Response response)
    {
        return new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
    }
}
