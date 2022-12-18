package APiTestingPRoject.recap;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class util {


    public static String Token(){

        Faker faker=new Faker();

        String clientName=faker.name().fullName();
        String customerEmail=faker.internet().emailAddress();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("clientName",clientName);
        jsonObject.put("clientEmail",customerEmail);

        String body=jsonObject.toString();


        RequestSpecification requestSpecification=given().header("Content-Type","application/json")
                .body(body);

        Response postresponse=requestSpecification.when().post("/api-clients");
        String token=postresponse.jsonPath().getString("accessToken");
        postresponse.then().assertThat().statusCode(201);



        return "Bearer "+postresponse.jsonPath().getString("accessToken");
    }



}
