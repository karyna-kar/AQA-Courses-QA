import helpers.Bodies;
import helpers.EndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import helpers.APISpecification;
import pages.Parser;

import static io.restassured.RestAssured.given;

public class BeforeRequests {
    protected static RequestSpecification requestSpec = APISpecification.getRequestSpecification();

    @BeforeTest
    static void setUp() {
        Response response = given().spec(requestSpec)
                    .when()
                    .body(Bodies.getRequestTokenBody())
                    .post(EndPoints.TOKEN);
//dd
        String token = Parser.getToken(response);
        requestSpec.header("x-xapp-token", token);
    }
}
