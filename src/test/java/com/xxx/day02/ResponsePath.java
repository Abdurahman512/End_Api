package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ResponsePath extends HrTestBase {

    @Test
    public void getSingleRegion () {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when()
                .get("/regions/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("Americas", response.path("region_name"));
        assertEquals(2, (Integer) response.path("region_id"));

        List<Map<String, String>> links = response.path("links");
        for (Map<String, String> eachLink : links) {
            System.out.println(eachLink.get("rel"));
            System.out.println(eachLink.get("href"));
            System.out.println("-----------------");

            List<String> allHref = response.path("links.href");
            System.out.println(allHref);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)  // skip one line
    public void parameterizedTest(int id, String regionName) {

        System.out.println(id+"   --> "+ regionName);

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", id)
                .when()
                .get("/regions/{id}");

        assertEquals(200, response.statusCode());
        assertEquals(regionName, response.path("region_name"));
        assertEquals(id, (Integer) response.path("region_id"));
    }
}
