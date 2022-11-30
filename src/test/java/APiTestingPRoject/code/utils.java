package APiTestingPRoject.code;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class utils {


    public static String bookId(){
        Response response=get("/books");
        response.then().assertThat().statusCode(200);
        String bookId=response.jsonPath().getString("[0].id");
        return  bookId;
    }

    public static String bearerToken(){

        Faker faker =new Faker();
        String clientName=faker.name().fullName();
        String clientEmail=faker.internet().emailAddress();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("clientName",clientName);
        jsonObject.put("clientEmail",clientEmail);

        String requestPayload=jsonObject.toString();


        //Given     i need to provide body, anyheaders if needed
        RequestSpecification generateTokenRequest=
                given().header("content-type","application/json")
                .body(requestPayload);

        Response generatetokenResponse=generateTokenRequest.when().post("/api-clients");

        generatetokenResponse.then().statusCode(201);

        System.out.println(generatetokenResponse.jsonPath().getString("accessToken"));
        return "Bearer "+ generatetokenResponse.jsonPath().getString("accessToken");



    }
}
