package pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class Parser {


    public static String getToken(Response response)
    {
        String token = getJsonObjectFromResponse(response).get("token").getAsString();
        return token;
    }

    public static JsonObject getJsonObjectFromResponse(Response response)
    {
        return new JsonParser().parse(response.getBody().asString()).getAsJsonObject();

    }

    public static Integer getTotalCountFromSearch(Response response)
    {
        int count = getJsonObjectFromResponse(response)
                .get("total_count").getAsInt();

        return count;
    }

    public static String getArtistLinkFromSearch(Response response)
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

    public static String getArtworkLinkFromSearch(Response response)
    {
        String  artwork = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("results").getAsJsonArray()
                .get(2).getAsJsonObject()
                .get("_links").getAsJsonObject()
                .get("self").getAsJsonObject()
                .get("href").getAsString();

        return artwork;
    }

    public static String getAuthorIDFromSearch(Response response)
    {
        String link =  getArtistLinkFromSearch (response);
        String[] parts = link.split("/");
        return parts[5];
    }

    public static String getArtworkIDFromSearch(Response response)
    {
        String link =  getArtworkLinkFromSearch (response);
        String[] parts = link.split("/");
        return parts[5];
    }

    public static String getNameFromGetArtist(Response response)
    {
        String name = getJsonObjectFromResponse(response)
                .get("name").getAsString();
        return name;
    }

    public static String returnName(String defaultName)
    {
        String[] parts = defaultName.split("\\+");
        return parts[0]+" "+parts[1];
    }
}
