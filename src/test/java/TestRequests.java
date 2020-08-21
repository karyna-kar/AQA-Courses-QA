import helpers.APISpecification;
import helpers.Bodies;
import helpers.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Parser;

import static io.restassured.RestAssured.given;

public class TestRequests extends BeforeRequests{

    private String artist = "Richard+Mayhew";
    private String artistID;
    private String artworkID;

    //Step1 - Search for Artist
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
        artworkID=Parser.getArtworkIDFromSearch(response);

        System.out.println(artworkID);
    }

    //Step2 - Get the Artist by ID + check the name
    @Test(dependsOnMethods={"searchArtistTest"})
    public void getArtistTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.ARTISTS+artistID);

        Assert.assertEquals(Parser.getNameFromGetArtist(response), Parser.returnName(artist));
    }

   /* //Step3 - Get the Artist by ID + check the name
    @Test(dependsOnMethods={"searchArtistTest"})
    public void getArtistWorksTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.ARTISTS+artistID);

        Assert.assertEquals(Parser.getNameFromGetArtist(response), "Richard Mayhew, Transgression (2008)");
    }*/
}
