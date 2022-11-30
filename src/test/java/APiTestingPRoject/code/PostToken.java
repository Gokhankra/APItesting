package APiTestingPRoject.code;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PostToken {

    String baseURI= RestAssured.baseURI="https://simple-books-api.glitch.me";


    @Test(description = "Given a baseURi When We make POSTT call to/api-clients Then Verify if Acces Token is available")
    void verifyAccesToken(){




    }
}
