import helpers.EndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Parser;
import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class TestRequests extends BeforeRequests{

    private final String  artist = "Gustav+Klimt";
    private String artistID;
    private String artworkID;

    // Search for Artist
    @Test
    public void searchArtistTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.SEARCH+artist);

        Assert.assertNotEquals(Parser.getTotalCountFromSearch(response), 0);
        artistID =  Parser.getAuthorIDFromSearch(response);
    }

    //Get the Artist by ID + check the name
    @Test(dependsOnMethods={"searchArtistTest"})
    public void getArtistTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.ARTISTS+"/"+artistID);

        Assert.assertEquals(Parser.getNameFromGetArtist(response), "Gustav Klimt");
    }

    //Get the Artist Works by Artist ID
    @Test (dependsOnMethods={"getArtistTest"})
    public void getArtistWorksTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
               .get(EndPoints.ARTWORKS+"?artist_id="+artistID);

        Assert.assertEquals(Parser.getArtworkTitleFromSearch(response),"Der Kuss (The Kiss)");
        artworkID = Parser.getArtworkID(response);
    }

    // Get the Artist Work by ID + created / updated date
    @Test ( dependsOnMethods={"getArtistWorksTest"})
    public void checkWorkDateTest(){
        ArrayList<String> expectedTimes = new ArrayList<String>(Arrays.asList("2010-12-20T19:48:55+00:00", "2020-07-14T19:14:01+00:00"));

        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.ARTWORKS+"/"+artworkID);

        Assert.assertEquals(Parser.getArtworkDates(response).toArray(), expectedTimes.toArray());
    }

    //Get the Artist Genes + check there are  >0
    @Test(dependsOnMethods={"getArtistWorksTest"})
    public void getArtistGenesTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.GENES+"?artist_id="+artistID);

        System.out.println(Parser.getNumberOfGenes(response));
        Assert.assertNotEquals(Parser.getNumberOfGenes(response),0);
    }
}
