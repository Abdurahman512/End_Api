package com.xxx.day01;

import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanTest extends SpartanTestBase {
    /*
    Send request to Spartan url and save the respone
    Accept application/json
    Get/spartans
    Store the response in Response object that comes from GET request
    print out followings
        -Response
        -Content-type
        -Status code
        -Get me first spartan gender
        -Get me first spartan name
        -Get me all spartan name
     */

    @DisplayName("GET/spartans")
    @Test
    public void getAllSpartans() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .when()
                .get("/spartans");

        // -Status code
        System.out.println("response.statusCode() = " + response.statusCode());

        // -Response
       //  response.prettyPrint();

        // -Content-type
        assertEquals(ContentType.JSON.toString(),response.contentType());

        // -Get me first spartan gender
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));

        // -Get me first spartan gender
        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

        List<String> names = response.path("name");
        for (String name : names) {
            System.out.println("name = " + name);
        }
        System.out.println("names.size() = " + names.size());
    }
}
