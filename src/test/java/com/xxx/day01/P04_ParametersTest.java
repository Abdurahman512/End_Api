package com.xxx.day01;

import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_ParametersTest extends SpartanTestBase {

    /*
    Given accept type is Json
    Path parameters values are
        id   -->    5
    When user sends GET request to /spartans/{id}
    Verify following
       -Status code should be 200
       -Content Type is application/json
       -Id is 5
       -Name is "Blythe"
       -gender is "Female"
       -phone is "3677539542"
     */

    @DisplayName("GET/spartans/{id}")
    @Test
    public void pathParam() {
        Response response =
                given()
                        .log().uri().accept(ContentType.JSON)
                        .pathParam("id",5)
                .when()
                        .get("/spartans/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,5);
        assertEquals(name,"Blythe");
        assertEquals(gender,"Female");
        assertEquals(phone,3677539542l);


    }

    @Test
    public void queryParam() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "f");
        queryMap.put("gender", "Female");

        Response response = given().log().uri().accept(ContentType.JSON)
                .when()
                .queryParams(queryMap)
                .get("/spartans/search");

        System.out.println("response.statusCode() = " + response.statusCode());
        // get me first spartan name
        System.out.println("response.path(\"name\") = " + response.path("content.name[0]"));
        System.out.println("response.path(\"content.id[1]\") = " + response.path("content.id[1]"));
        System.out.println("response.path(\"content.name[-1]\") = " + response.path("content[-1].name"));


        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        List<String> allNames = response.path("content.name");
        System.out.println("allNames = " + allNames);
    }

    @DisplayName("/GET/spartans/500")
    @Test
    public void negativeTest() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when()
                .get("/spartans/{id}");

        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        System.out.println("response.path(\"error\") = " + response.path("error"));
        assertEquals("Not Found",response.path("error"));

        assertTrue(response.asString().contains("Not Found"));
        System.out.println("response.asString() = " + response.asString());
    }
}
