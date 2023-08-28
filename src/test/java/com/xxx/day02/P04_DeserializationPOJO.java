package com.xxx.day02;

import com.xxx.pojo.Customer;
import com.xxx.pojo.CustomerData;
import com.xxx.utility.FruitApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P04_DeserializationPOJO extends FruitApiTestBase {

    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * Query params start is 1 and limit is 2
     * GET /customers/
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json
     *     - Status Code is 200
     *     - Retrieve data as POJO Class and print out into screen
     *
     *      PAGINATION
     *     - Store all customer information in a list and print all names
     */

    @Test
    public void getCustomersPOJO() {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("start",1);
        queryMap.put("limit",2);

        JsonPath jp = given().log().uri()
                .queryParams(queryMap)
                .accept(ContentType.JSON).
                when().get("/customers").
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
        CustomerData customerData = jp.getObject("", CustomerData.class);

        System.out.println(customerData);

    }
}