package com.xxx.day02;

import com.xxx.pojo.Search;
import com.xxx.pojo.Spartan;
import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P07_JsonToPOJO extends SpartanTestBase {
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

        /*
        {
    "id": 10,
    "name": "Lorenza",
    "gender": "Female",
    "phone": 3312820936
}
         */

        System.out.println("-----First Approach  --- Response.as() --------");

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());

        System.out.println("-----First Approach  --- Response.path() --------");

        JsonPath jsonPath = response.jsonPath();
        Spartan spartan1 = jsonPath.getObject("", Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());


    }

    @Test
    public void searchSpartans() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .queryParam("nameContains", "f")
                .when()
                .get("/spartans/search")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        System.out.println("----- GET me first spartan  --- response.as() --------");
       // response.as() --> we are not gonna use response.as() method to get partial of response
        // as POJO class, it does not have path parameter to do it

        System.out.println("----- GET me first spartan  --- JsonPath() --------");
        JsonPath jsonPath = response.jsonPath();
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("spartan = " + spartan);
        System.out.println("spartan.getName() = " + spartan.getName());
    }
    @Test
    public void searchSpartanPOJO() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("gender", "Female")
                .queryParam("nameContains", "f")
                .when()
                .get("/spartans/search")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Search search = jsonPath.getObject("", Search.class);

        System.out.println("search.getTotalElement() = " + search.getTotalElement());

        List<Spartan> content = search.getContent();
        System.out.println("content = " + content);
        System.out.println(search.getContent().size());

        // first spartan info
        System.out.println(content.get(0));

        // first spartan name
        System.out.println(content.get(0).getName());

    }
}
