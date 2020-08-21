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

    //
    @Test
    public void searchArtistTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.SEARCH+artist);

        String authorID =  Parser.getAuthorIDFromSearch(response);
        System.out.println(authorID);
    }

    @Test
    public void getArtistTest(){
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.ARTISTS+artistID);

    }
}
