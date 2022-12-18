package APiTestingPRoject.recap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestWithQueryParam {
    String baseuri=RestAssured.baseURI="https://simple-books-api.glitch.me/";


    @Test(description = "Test with request Specification ")

    void TestRequesSpecification(){
        // genel bir gosterge oldugu icin queryparams olmasi lazim
        RequestSpecification requestSpecification=given().queryParams("limit",1);// it will show only 2 items in BODY
        Response getresponse=requestSpecification.when().get("/books");
        getresponse.then().assertThat().statusCode(200);
        System.out.println(getresponse.getTime());
        System.out.println(getresponse.getBody().asString());
        System.out.println("**************");
    }


    @Test(description = "testing with two QUERY PARAMS ")
    void TestWith2QueryParams(){

        RequestSpecification requestSpecification=given().queryParams("limit",2,"type","fiction");
        Response response=requestSpecification.when().get("/books");
        response.then().assertThat().statusCode(200);
        String Body=response.getBody().asString();
        System.out.println(Body);
        String bookname=response.jsonPath().getString("[1].name");
        System.out.println(bookname);
        System.out.println(response.jsonPath().getString("[1].id"));
        Assert.assertEquals(response.jsonPath().getString("[1].id"),"3");
        System.out.println(response.getTime());
    }



}
