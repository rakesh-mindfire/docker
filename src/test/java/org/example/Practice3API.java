package org.example;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Practice3API {
    @Test
    public void m1(){
        Response response=RestAssured.given().baseUri("https://reqres.in/")
                .headers("x-api-key","reqres-free-v1")
                .queryParam("page","2")
                .log().all()
                .when().get("api/users");

        response .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .assertThat().statusLine("HTTP/1.1 200 OK")
                .log().all();
        Assert.assertEquals(response.getBody().jsonPath().get("page").toString(),"2");

    }
    @Test
    public void m2(){
        HashMap<String,String> map=new HashMap<>();
        map.put("name","morpheus");
        map.put("job","leader");


        Response response=RestAssured.given().baseUri("https://reqres.in/")
                .headers("x-api-key","reqres-free-v1")
                //.queryParam("page","2")
                .body(map)
                .contentType(ContentType.JSON)
                .log().all()
                .when().post("api/users");
response.then().log().all();
        response .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().statusCode(201)
                .assertThat().statusLine("HTTP/1.1 201 Created");
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader");


    }
}
