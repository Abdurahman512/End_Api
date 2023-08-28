package com.xxx.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class P02_BeforAll_AfterAll {

    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.231.66:1000";
        basePath = "/ords/hr";
    }
    @AfterAll
    public static void destroy() {
        reset();
    }

    @Test
    public void simpleTest () {

        Response response = RestAssured.get("/regions");

        response.prettyPrint();
        // -Headers
        System.out.println("response.headers() = " + response.headers());
        System.out.println("---------------------------");
        System.out.println("response.getHeaders() = " + response.getHeaders());

        System.out.println("---------------------------");
        // -Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());

        // -Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println(response.headers().hasHeaderWithName("Date"));

        //Verify response has Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // -Verify response body has Europe
        Assertions.assertTrue(response.asString().contains("Europe"));
    }

    /*
    send request to HR url and save the respone
    GET/employees/100
    store the response in Response object that comes from GET request
    print out followings
       -First Name
       -Last Name
       -Verify status code is 200
       -Verify First Name is Steven
       -Verify content-type is application/json

     */

    @DisplayName("GET/employees/100")
    @Test
    public void getOneEmployee() {
        Response response = get("/employees/100");
        // -First Name
        String first_name = response.path("first_name");
        System.out.println("first_name = " + first_name);

        // -Last Name
        String last_name = response.path("last_name");
        System.out.println("last_name = " + last_name);

        // -Verify status code is 200
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

        // -Verify First Name is Steven
        Assertions.assertEquals(first_name,response.path("first_name"));

        // -Verify content-type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
    }
}
