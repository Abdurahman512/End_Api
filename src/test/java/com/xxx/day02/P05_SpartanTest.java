package com.xxx.day02;

import com.xxx.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P05_SpartanTest extends SpartanTestBase {

    /*
    send a request to GET/spartans/search
    query parameters values are
         gender   --  Female
         nameContains -- ea
    log everything
    verify followings
       status code is 200
       content type is application/json
       total element 11
       jsonArray size hasSize 11
       names hasItem "Alfy"
       every gender is Female

     */
    @Test
    public void searchSpartans() {
        given().accept(ContentType.JSON)
                .queryParam("gender","Female")
                .queryParam("nameContains","f")
        .when()
                .get("/spartans/search")
        .then()
                .log().ifValidationFails()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .body("totalElement",is(11))
                .body("content",hasSize(11))
                .body("content.name",hasItem("Alfy"))
                .body("content.gender",everyItem(is("Female")))
                ;
    }
}
