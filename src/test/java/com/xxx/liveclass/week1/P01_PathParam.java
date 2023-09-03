package com.xxx.liveclass.week1;

import com.xxx.utility.FruitApiTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_PathParam extends FruitApiTestBase {
    /**
     *1- Given accept type is Json
     *2- Path Parameters value is
     *     - id —> 1
     *3- When user sends GET request to /products/{id}
     *4- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - Print response
     *     - Name is "Banana"
     *     - id should match with path param
     *
     */

    @Test
    public void getSingleProduct() {
        Response response = given()
                .log().uri().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        System.out.println(response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        System.out.println(response.contentType());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        response.prettyPeek();
        System.out.println("name = " + response.path("name"));

        assertEquals(1, (Integer) response.path("id"));
    }

    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON)
                .log().uri()
                .pathParam("id", 1)
                .when()
                .get("/vendors/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        String name = response.path("name");
        assertEquals("Exotics Fruit Lair Ltd.",name);
    }
}
