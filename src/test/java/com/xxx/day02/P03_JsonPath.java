package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_JsonPath extends HrTestBase {

    /*
    Given
               accept type is Json
          When
               user sends GET request to /locations
          Then
               Status code should be 200
               content type ist application/json
               get the second city with jsonpath
               get the last city with JsonPath
               get all country ids
               get all city where their country id is UK
     */
    @Test
    public void getLocations() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("locations");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        JsonPath jsonPath = response.jsonPath();

        // get the second city with jsonpath
        System.out.println("second city = " + jsonPath.get("items[1].city"));
        // get the last city with JsonPath
        System.out.println("last city = " + jsonPath.get("items[-1].city"));
        // get all country ids
        List<String> country_id = jsonPath.get("items.country_id");
        System.out.println("country_id = " + country_id);

        // get all city where their country id is UK
        List<String> allCityUK = jsonPath.getList("items.findAll{it.country_id=='UK'}.city");
        System.out.println("allCityUK = " + allCityUK);
    }

    /*
    Given
               accept type is Json
          When
               user sends GET request to /employees
          Then
               Status code should be 200
               get me all employees first_name who is making more than 15000
     */
    @Test
    public void getSingleEmployee() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/employees");

        System.out.println("response.statusCode() = " + response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        List<String> first_name15000 = jsonPath.getList("items.findAll{it.salary>15000}.first_name");

        System.out.println("first_name15000 = " + first_name15000);
    }
}
