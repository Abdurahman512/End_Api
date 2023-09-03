package com.xxx.liveclass.week1;

import com.xxx.utility.FruitApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_DeserializationJava extends FruitApiTestBase {
    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /customers
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json
     *     - Status Code is 200
     *     - Retrieve data as JAVA Collections and print out following informations
     *
     System.out.println("====== GET META ======");
     System.out.println("====== GET LIMIT ======");
     System.out.println("====== GET CUSTOMERS ======");
     System.out.println("====== GET FIRST CUSTOMER ======");
     System.out.println("====== PRINT CUSTOMERS IDs ======");
     System.out.println("====== PRINT CUSTOMERS Names ======");

     *
     */
    @Test
    public void getCustomers() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("start", 1)
                .queryParam("limit", 100)
                .when()
                .get("/customers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Map<String, Object> allData = jsonPath.getMap("");

        System.out.println("====== GET META ======");
        Map<String, Integer> meta = (Map<String, Integer>) allData.get("meta");
        System.out.println(meta);

        System.out.println("====== GET LIMIT ======");
        System.out.println(meta.get("limit"));

        System.out.println("====== GET CUSTOMERS ======");
        List<Map<String, Object>>customers = (List<Map<String, Object>>) allData.get("customers");
        System.out.println(customers);

        System.out.println("====== GET FIRST CUSTOMER ======");
        Map<String, Object> firstCustomer = customers.get(0);
        System.out.println(firstCustomer);

        System.out.println("====== PRINT CUSTOMERS IDs ======");
        List<Object> allIDs = customers.stream().map(eachCustomer -> eachCustomer.get("id")).collect(Collectors.toList());
        System.out.println(allIDs);

        System.out.println("====== PRINT CUSTOMERS Names ======");
        List<Object> allNames = customers.stream().map(eachCustomer -> eachCustomer.get("name")).collect(Collectors.toList());
        System.out.println(allNames);
    }
}
