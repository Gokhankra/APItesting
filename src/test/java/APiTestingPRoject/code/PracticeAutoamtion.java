package APiTestingPRoject.code;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.management.Query;

import static io.restassured.RestAssured.given;

public class PracticeAutoamtion {

    String baseuri="https://simple-books-api.glitch.me";


    @Test(description = "another test for multi steps")
    void TC1(){

        RequestSpecification request=given().queryParam("Limit",5);

        Response response=request.when().get("https://simple-books-api.glitch.me/books");

        response.then().assertThat().statusCode(200);

        System.out.println(response.getBody().asString());
        //starts from 0 due to index
        String Thirdbookname =response.jsonPath().getString("[2].name");
        String Thirdbookid=response.jsonPath().getString("[2].id");

        Assert.assertEquals(Thirdbookid,"3");
        System.out.println(Thirdbookname);

    }


    @Test(description = "Given a baseUri when we make the get call to books and query parameter as 1 then verify Status code is 200")
    void userRetrieveListOfBooks(){

       //given
        RequestSpecification request=given().queryParams("type","fiction","limit",2);


        //when
        Response response=request.when().get(baseuri.concat("/books"));

        //Then
        response.then().assertThat().statusCode(200);

        System.out.println(response.getBody().asString());
        String bookname=response.jsonPath().getString("[1].name");
        String type=response.jsonPath().getString("[1].type");
        Assert.assertEquals(type,"fiction","type is fiction failed");
        System.out.println(bookname+ "     "+type);

        String id=response.jsonPath().getString("[1].id");
       String isavailablity=response.jsonPath().getString("[0].available");
       Assert.assertEquals(isavailablity,"true");  // burda string true olarak donuyor deger true degil javadaki

        Assert.assertEquals(id,"3");
        //cuz second book on the retrieved list id is 3 *****************

    }

    @Test(description = "Given a baseURI when we make aget call to books and tyoe=crime(doesnt exist) Then verify Status code is 400")
    void retrievebookwhichisnotexist(){

        //Given
        RequestSpecification request=given().queryParam("type","crime");


        //When
        Response response=request.when().get(baseuri+"/books");

        //Then
        response.then().assertThat().statusCode(400);
        System.out.println(response.getBody().asString());

    }



}
