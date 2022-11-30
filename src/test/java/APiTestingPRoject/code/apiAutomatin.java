package APiTestingPRoject.code;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class apiAutomatin {


    String baseurl= baseURI="https://simple-books-api.glitch.me";

    String bookidfromutil=utils.bookId();
    String baseuri="https://simple-books-api.glitch.me";

    @Test(description = "3rd day with trying again reqestpesicifation")
    void TestDeneme(){
        RequestSpecification request=given().queryParam("type","fiction");
        Response response=request.when().get(baseuri+"/books");

        System.out.println(response.getBody().asString());

        response.then().assertThat().statusCode(200);

        String bookname=response.jsonPath().getString("[0].name");
        System.out.println(bookname);
    }

    @Test(description = "Given baseURI when we make Get call to / books/:bookId endpoint  Then verify status code and Get Book Information")
    void TestGivenbyQueryPAramet(){
        String bookId="1";
        //given
        RequestSpecification request=given().pathParam("bookId",bookidfromutil);
        //when
        Response response=request.when().get("/books/{bookId}");


        //then
        response.then().assertThat().statusCode(200);

        System.out.println(response.getBody().asString());
        String bookid=response.jsonPath().getString("id");
        System.out.println(bookid);


    }
}
