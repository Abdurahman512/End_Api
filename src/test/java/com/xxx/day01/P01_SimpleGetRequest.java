package com.xxx.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P01_SimpleGetRequest {

    @Test
    public void simpleTest () {

        Response response = RestAssured.get("http://52.91.231.66:1000/ords/hr/regions");

        System.out.println("response.headers() = " + response.headers());
        System.out.println("---------------------------");
        System.out.println("response.getHeaders() = " + response.getHeaders());

        System.out.println("---------------------------");
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println(response.headers().hasHeaderWithName("Date"));

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        Assertions.assertTrue(response.asString().contains("Europe"));
    }
}
