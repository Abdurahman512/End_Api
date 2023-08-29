package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_Contains extends HrTestBase {

    /*
      Given
           accept type is Json
      When
           user sends GET request to /regions/{id}
      Then
           Status code should be 200
           Content Type is application/json
           response body contains Americas
     */
    @Test
    public void getSingleRegion() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when().get("/regions/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        assertEquals(ContentType.JSON.toString(),response.contentType());

        String region_name = response.path("region_name");
        assertEquals(region_name,"Americas");
        assertTrue(response.asString().contains("Americas"));
    }
}
