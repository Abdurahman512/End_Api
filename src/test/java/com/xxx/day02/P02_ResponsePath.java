package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_ResponsePath extends HrTestBase {

    /*
          Given
               accept type is Json
          When
               user sends GET request to /regions/2
          Then
               Status code should be 200
               region_name is Americas
               region_id is 2
               print out all the link
         */
    @Test
    public void getSingleRegion() {
        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when()
                .get("/regions/{id}");

        // Status code should be 200
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        // region_name is Americas
        assertEquals("Americas",response.path("region_name"));

        // region_id is 2
        assertEquals(2, (Integer) response.path("region_id"));

        List<Map<String, String>> links = response.path("links");
        for (Map<String, String> eachLink : links) {
            System.out.println(" = " + eachLink);
            System.out.println("-----------------");
        }

        // get me all href info
        List<String> allHref = response.path("links.href");
        for (String eachHref : allHref) {
            System.out.println("eachHref = " + eachHref);
            System.out.println("------------------------");
        }
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void parameterizedTest(int id, String region_name) {

        System.out.println(id+"----"+region_name);

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/regions/{id}");

        // Status code should be 200
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        // region_name is Americas
        assertEquals(region_name,response.path("region_name"));

        // region_id is 2
        assertEquals(id, (Integer) response.path("region_id"));
    }

}
