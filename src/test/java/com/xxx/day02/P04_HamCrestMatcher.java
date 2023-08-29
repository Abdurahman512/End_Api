package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HamCrestMatcher extends HrTestBase {

    /*
    Given
               accept type is Json
          When
               user sends GET request to /regions
          Then
               Status code should be 200
               content type ist application/json
               verify Data has values
               first region name is Europe
               Regions name should be same order as "Europe, Americas, Asia, Middle East and Africa"
               region ids needs to be 1,2,3,4
     */
    @Test
    public void getAllRegions() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/regions")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .header("Date",notNullValue())
                .body("items[0].region_name",is("Europe"))
                .body("items.region_name",containsInRelativeOrder
                        ("Europe", "Americas", "Asia", "Middle East and Africa"));
    }
}
