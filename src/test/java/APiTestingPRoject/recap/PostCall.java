package APiTestingPRoject.recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.Token;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostCall {

    //what we need for post call
    // Token,body,
    @BeforeClass
    void Setup(){

        RestAssured.baseURI="https://simple-books-api.glitch.me";

    }
@Test(description = "post call to order a book ")
    void  postORder(){

        String token=util.Token();
    Faker faker=new Faker();
    int bookId=faker.random().nextInt(1,6);
    String customerName=faker.name().fullName();

    JSONObject jsonObject=new JSONObject();
    jsonObject.put("bookId",1);
    jsonObject.put("customerName",customerName);

    String bodyload=jsonObject.toString();

    RequestSpecification requestSpecification=given()
            .header("Content-Type","application/json")
            .header("Authorization", token)
            .body(bodyload);

    Response response=requestSpecification.when().post("/orders");

    response.then().assertThat().statusCode(201);

    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());
        String orderId=response.jsonPath().getString("orderId");
    System.out.println(orderId);

    //List the order that you post to check if  order process is correct
    RequestSpecification reqGEt=given().header("Authorization",token);

    Response getList=reqGEt.when().get("/orders");

    getList.then().assertThat().statusCode(200);

    System.out.println(getList.getBody().asString());
    System.out.println(" Next is Patch Call");

    String cusname=getList.jsonPath().getString("customerName");
    System.out.println(cusname);
    System.out.println(customerName);
    Assert.assertTrue(cusname.contains(customerName));

    //update the order before you ordered to See how Patch call work

    Faker faker1=new Faker();
    String newCustomer=faker1.name().fullName();
    JSONObject jsonObject1=new JSONObject();
    jsonObject1.put("customerName",newCustomer);
    String customerNew=jsonObject1.toString();
    RequestSpecification  patchRequest=given()
            .pathParam("orderId",orderId)
            .header("Content-Type","application/json")
            .header("Authorization",token)
            .body(customerNew);

    Response patchResponse=patchRequest.when().patch("/orders/{orderId}");

    patchResponse.then().assertThat().statusCode(204);

    System.out.println(patchResponse.getBody().asString());
    System.out.println(patchResponse.getHeaders().toString());

    // after patch get call again to see if it is patched

    RequestSpecification requestSpecification1=given().header("Authorization",token);

    Response secondgetresponse=requestSpecification1.when().get("/orders");

    secondgetresponse.then().assertThat().statusCode(200);

    System.out.println(secondgetresponse.getBody().asString());

    //Delete Call
    JSONObject jsonObject2=new JSONObject();
    jsonObject2.put("","");
    String delete=jsonObject2.toString();
    RequestSpecification deleteRequest=given().pathParam("orderId",orderId)
            .header("Content-Type","application/json")
            .header("Authorization",token)
            .body("{}");
    Response deleteresponse=deleteRequest.when().delete("/orders/{orderId}");

    deleteresponse.then().assertThat().statusCode(204);



    RequestSpecification last=given().header("Authorization",token);

    Response lastreviev=last.when().get("/orders");
    lastreviev.then().assertThat().statusCode(200);

    System.out.println(lastreviev.getBody().asString());
}




}
