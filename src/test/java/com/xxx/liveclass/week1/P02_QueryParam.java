package com.xxx.liveclass.week1;

import com.xxx.utility.FruitApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_QueryParam extends FruitApiTestBase {

    /**
     *1- Given accept type is Json
     *2- Query Parameters value is
     *     start —> 1
     *     limit -> 100
     *     search -> "Fruit"
     *3- When user sends GET request to /products
     *4- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - start and limit values are matching with query params
     *     - product names contains Fruit
     *
     */
    @Test
    public void getProducts() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("start", 1)
                .queryParam("limit", 100)
                .queryParam("search", "Fruit")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        int start = response.path("meta.start");
        int limit = response.path("meta.limit");

        assertEquals(1,start);
        assertEquals(100,limit);

        assertTrue(response.path("products.name").toString().contains("Fruit"));

        JsonPath jsonPath = response.jsonPath();
        int star = jsonPath.get("meta.start");
        System.out.println(star);

        List<String> allProduct = jsonPath.getList("products.name");
        assertTrue(allProduct.stream().allMatch(eachName->eachName.contains("Fruit")));
    }

    @Test
    public void getProductsQueryMap() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("start",1);
        queryMap.put("limit",100);
        queryMap.put("search","Fruit");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParams(queryMap)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("meta.start", is(1))
                .body("meta.limit", is(100))
                .body("products.name", everyItem(containsString("Fruit")))
                .extract().jsonPath();


    }
}
