package APiTestingPRoject.code;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class GetStatus {

    @Test
    public void happyPathTest(){

        //this line will hlp us to make the call and save te response into "response" object
        Response response= RestAssured.get("https://simple-books-api.glitch.me/status");

            // this will print whole response
        System.out.println("Response : "+ response.asString());

        System.out.println(response.statusCode());
        if (response.statusCode()==200){
            System.out.println("test status is passed");
        }

        // print how long it take time to call
        System.out.println(response.time());
        System.out.println(response.getTime());


        //this will headers information
        System.out.println(response.getHeaders());
        System.out.println("content type is : "+response.getHeader("Content-Type"));


        //we can also print date
        String date=response.getHeader("Date");
        System.out.println(date);

        Assert.assertEquals(response.statusCode(),200,"it is failed status");
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.statusCode(),2011);

        //if no header it will return as null ;
        System.out.println(response.getHeader("asdk"));

        if (response.statusCode()==200 && response.getTime()<=1000){
                System.out.println("test is passed with validatet values" );
            }


     //   Response response1=RestAssured.post("https://simple-books-api.glitch.me/orders");


    }


    @Test(description = "Given  baseurl when we get call to /status then verify status is 200")
    public void ValidateStatusCode(){

        Response response=RestAssured.get("https://simple-books-api.glitch.me/status");
        int Actualstatuscode=response.getStatusCode();

        int expectedStatusCode=201;

        Assert.assertEquals(Actualstatuscode,expectedStatusCode,"Status code should be 200 but expected status code is "+expectedStatusCode);


    }


    @Test(description = "Given baseURI when we make get call to /status endpoint Then Verify time of respnse is 2000ms")
    public void ValidateTime(){


        Response response=RestAssured.get("https://simple-books-api.glitch.me/books");
        long actualtime=  response.getTime();
        long expectedtime=2000;

        String  ry=response.getBody().asString();
        System.out.println(ry);

        ArrayList<String> books=new ArrayList<>();

        for (int i = 0; i <ry.length() ; i++) {

            if ("id".equals(ry.substring(i,i+2))){
                books.add(ry.substring(i+2,i+4));
            }else {
                continue;
            }


        }
        System.out.println(books);


        System.out.println(actualtime);
        System.out.println(expectedtime);
        Assert.assertTrue(actualtime<=expectedtime,"Test is failed ");
    }



    @Test(description = "userretrieveListofBooksLimit2()")
    void userretrievelistOfThebooksLimit2(){

        //method chaning

        // given().queryParam("limit",2).when().get("https://www.simple.com/books").then().assertThat().statusCode(200);

        //  in the code snippet below, we are providing a query param called "limit" using RequestSpecification class
        RequestSpecification request=given().queryParam("limit",5);

        Response response=request.when().get("https://simple-books-api.glitch.me/books");

        //we are validatiing status code
        response.then().assertThat().statusCode(200);

        //print response body
        System.out.println(response.getBody().asString());

        //get all headers
        System.out.println(response.getHeaders());

        // we will get the info of second book name
        String bookname= response.jsonPath().getString("[2].name");
        System.out.println(bookname);
        String booktype=response.jsonPath().getString("[1].type");
        System.out.println(booktype);



    }


}
