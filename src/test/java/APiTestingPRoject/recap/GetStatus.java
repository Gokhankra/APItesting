package APiTestingPRoject.recap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetStatus {
    String basuri=RestAssured.baseURI="https://simple-books-api.glitch.me";



    @Test(description = "Get Status with base uti")

    void GetTestStatus(){
        Response response=RestAssured.get("/books");
        System.out.println(response.asString());

        //this will check for time
        System.out.println(response.getTime());

        //this will get all headers
        System.out.println(response.getHeaders());
        System.out.println(response.getBody().asString());


    }


}
