import helpers.Bodies;
import helpers.EndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import helpers.APISpecification;
import pages.Parser;

import static io.restassured.RestAssured.given;

public class BeforeRequests {
    protected static RequestSpecification requestSpec = APISpecification.getRequestSpecification();
    protected static ResponseSpecification responseSpec = APISpecification.getResponseSpecification();

    @BeforeTest
    static void setUp() {
        Response response = given().spec(requestSpec)
                    .body(Bodies.getRequestTokenBody())
                    .log().all()
                    .when()
                    .post(EndPoints.TOKEN);

        String token = Parser.getToken(response);
        requestSpec.header("x-xapp-token", token);
    }
}
