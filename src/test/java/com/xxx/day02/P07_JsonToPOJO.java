package com.xxx.day02;

import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

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
    }
}
