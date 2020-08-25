package pages;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import java.util.ArrayList;

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
                .get(1).getAsJsonObject()
                .get("_links").getAsJsonObject()
                .get("self").getAsJsonObject()
                .get("href").getAsString();
        return artwork;
    }

    public static String getArtworkTitleFromSearch(Response response)
    {
        String  title = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("artworks").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("title").getAsString();
        return title;
    }

    public static String getAuthorIDFromSearch(Response response)
    {
        String link =  getArtistLinkFromSearch (response);
        String[] parts = link.split("/");
        return parts[5];
    }

    public static String getArtworkID(Response response)
    {
        String artworkID = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("artworks").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("id").getAsString();
        return artworkID;
    }

    public static String getNameFromGetArtist(Response response)
    {
        String name = getJsonObjectFromResponse(response)
                .get("name").getAsString();
        return name;
    }

    public static Integer getNumberOfGenes(Response response)
    {
        int  size = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("genes").getAsJsonArray().size();
        return size;
    }
    public static ArrayList<String> getArtworkDates(Response response)
    {
        ArrayList<String> actualTimes = new ArrayList<String>();
        actualTimes.add(getJsonObjectFromResponse(response).get("created_at").getAsString());
        actualTimes.add(getJsonObjectFromResponse(response).get("updated_at").getAsString());
        return actualTimes;
    }
}
