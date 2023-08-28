package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Contains extends HrTestBase {

    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when()
                .get("/regions/{id}").prettyPeek();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.asString().contains("Americas"));

    }
}
