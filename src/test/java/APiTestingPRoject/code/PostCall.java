package APiTestingPRoject.code;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PostCall {


    String baseURI= RestAssured.baseURI="https://simple-books-api.glitch.me";

        @Test(description = "making post call with bearer token")

    void accestoken(){
            System.out.println(utils.bearerToken());
        }

}
