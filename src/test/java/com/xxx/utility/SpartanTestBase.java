package com.xxx.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init () {

        baseURI = "http://52.91.231.66:8000";
        basePath = "/api";
    }
    @AfterAll
    public static void destroy () {
        reset();
    }
}
