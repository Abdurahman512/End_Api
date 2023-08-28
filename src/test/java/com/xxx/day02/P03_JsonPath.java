package com.xxx.day02;

import com.xxx.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_JsonPath extends HrTestBase {

    @Test
    public void getLocations() {

        Response response = given().contentType(ContentType.JSON).
                when()
                .get("/locations");

        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        JsonPath jsonPath = response.jsonPath();
        String secoundCity = jsonPath.getString("items[1].city");
        System.out.println(secoundCity);

        System.out.println(jsonPath.getString("items[-1].city"));

        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        List<String> allCityUK = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println(allCityUK);
    }


}
