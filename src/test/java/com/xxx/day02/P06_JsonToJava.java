package com.xxx.day02;

import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P06_JsonToJava extends SpartanTestBase {

    /*
    Given accept type is application7json
    And path param id is 10
    When I send GET request to /api/spartans
    Then status code is 200
    And content type is json
    And spartan data matching :
        id > 10
        name > Lorenza
        gender > Female
        phone > 3312820936

     */
    @Test
    public void getSingleSpartan() {

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("id", 10)
                .when()
                        .get("/spartans/{id}")
                .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

       // Map<String, Object> spartanMap  = response.as(Map.class);
        /* as() --> will do de-serilization and if we wanna convert Json to Map by using as() method it requires
        Jackson or Gson --> Databind / Objectmapper libraries
         */

        System.out.println("-----First Approach  --- Response.path() --------");
        Map<String, Object> spartanMap  = response.path("");
        System.out.println("spartanMap = " + spartanMap);

        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");

        System.out.println("id = " + id);
        System.out.println("name = " + name);

        System.out.println("-----First Approach  --- JsonPath --------");
        JsonPath jsonPath = response.jsonPath();
        Map<Object, Object> spMap = jsonPath.getMap("");

        int id1 = (int) spMap.get("id");
        String name1 = (String) spMap.get("name");
        System.out.println("id1 = " + id1);
        System.out.println("name1 = " + name1);
    }
    @Test
    public void getAllSpartan() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("-----First Approach  --- Response.path() --------");
        List<Map<String, Object>> allSpartans = response.path("");
        // print all spartans info
        for (Map<String, Object> eachSpartan : allSpartans) {
            System.out.println(eachSpartan);
        }
        // find first spartan info
        System.out.println("First spartan"+allSpartans.get(0));

        Map<String, Object> firstSpartan = allSpartans.get(0);
        String name = (String) firstSpartan.get("name");
        System.out.println(name);


        System.out.println(allSpartans.get(0).get("name"));

        System.out.println("-----First Approach  --- JsonPath --------");

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> listOfSpartans = jsonPath.getList("");

        for (Map<String, Object> eachSp : listOfSpartans) {
            System.out.println(eachSp);
        }
        System.out.println(listOfSpartans.get(1).get("name"));

    }
}
