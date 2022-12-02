package APiTestingPRoject.serializationAndDeserialization;

import APiTestingPRoject.code.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostOrderBookPOJO {

    @BeforeClass
    public void setup(){
        String baseurl= baseURI="https://simple-books-api.glitch.me";

    }
    @Test(description = "Given a baseURI and token When user wants to order book Then Verify status code is 201 ")
    void orderBook() {
        //First Call - Order Book
        //Needed information : request payload, token, endpoint and content-type

        //Given

        //Token
        String token = utils.bearerToken();

        //Payload
        Faker faker = new Faker();
        String customerName = faker.name().fullName();
        String bookId = utils.bookId();

        JSONObject object = new JSONObject();
        object.put("bookId", bookId);
        object.put("customerName", customerName);

        String requestPayload = object.toString();

        //Provide neccessary information

        RequestSpecification orderBookRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(requestPayload);

        //When

        Response orderBookResponse = orderBookRequest.when().post("/orders");

        //Then
        orderBookResponse.then().assertThat().statusCode(201);
        System.out.println("Response Payload for OrderBook:" + orderBookResponse.getBody().asString());

        String orderId = orderBookResponse.jsonPath().getString("orderId");
    }

    @Test(description = "given a baseuro and aut token and header when the user wants to order a book then verify if statsus code is 201")
    void orderbookPOJO() throws JsonProcessingException {

        //given
        //request patload,token,endpoint,any required header(content-type)

        String token=utils.bearerToken();

        //request pyload bookid,customername

        Faker faker=new Faker();
        String id=utils.bookId();
        String name=faker.name().fullName();

        //Create a pojo class set values as you wish

        orderbookPOJO requestBody=new orderbookPOJO(id,name);


        //converting A java class objecto to a json payload as String

        ObjectMapper objectMapper=new ObjectMapper();
        String jsonPayLoad=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);

        RequestSpecification orderBookrequest=given().header("Content-type","application/json")
                .header("Authorization",token)
                .body(jsonPayLoad);


        //When
        Response orderbookresponse=orderBookrequest.when().post("/orders");

        //Then
        orderbookresponse.then().assertThat().statusCode(201);

        System.out.println(orderbookresponse.body().asString());


        String orderId=orderbookresponse.jsonPath().getString("orderId");



        //Update order


        //Create a new name to update order
        String namefixed="Gokhan";

        orderbookPOJO orderbookPOJO=new orderbookPOJO("1",namefixed);
        ObjectMapper objectMapper1=new ObjectMapper();
        String updateORderJsonFormat=objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(orderbookPOJO);




        RequestSpecification updaterequest=given().pathParam("orderId",orderId).header("Authorization",token)
                .header("Content-type","application/json")
                .body(updateORderJsonFormat);

        Response updateresponse=updaterequest.when().patch("/orders/{orderId}");

        updateresponse.then().assertThat().statusCode(204);

        System.out.println(updateresponse.body().asString());



    }

}
