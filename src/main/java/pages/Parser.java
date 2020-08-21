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

    public static String getLinkFromSearch(Response response)
    {
        String link = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("results").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("_links").getAsJsonObject()
                .get("self").getAsJsonObject()
                .get("href").getAsString();
        return link;
    }

    public static String getAuthorIDFromSearch(Response response)
    {
        String link =  getLinkFromSearch (response);
        return link;
    }
}
