package com.xxx.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class FruitApiTestBase {

    @BeforeAll
    public static void init(){
        baseURI="https://api.predic8.de";
        basePath="/shop/v2";
    }

    @AfterAll
    public static void destroy(){

        reset();
    }

    }
