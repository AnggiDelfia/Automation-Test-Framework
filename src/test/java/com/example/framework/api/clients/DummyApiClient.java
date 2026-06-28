package com.example.framework.api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class DummyApiClient {
    private static final String DEFAULT_BASE_URL = "https://dummyapi.io/data/v1";
    private static final String DEFAULT_APP_ID = "63a804408eb0cb069b57e43a";

    private final String baseUrl;
    private final String appId;

    public DummyApiClient() {
        this.baseUrl = System.getProperty("dummyapi.baseUrl", DEFAULT_BASE_URL);
        this.appId = System.getProperty("dummyapi.appId",
                System.getenv().getOrDefault("DUMMY_API_APP_ID", DEFAULT_APP_ID));
    }

    public Response getUserById(String userId) {
        return baseRequest()
                .get("/user/{id}", userId);
    }

    public Response createUser(Map<String, Object> body) {
        return baseRequest()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/user/create");
    }

    public Response updateUser(String userId, Map<String, Object> body) {
        return baseRequest()
                .contentType(ContentType.JSON)
                .body(body)
                .put("/user/{id}", userId);
    }

    public Response deleteUser(String userId) {
        return baseRequest()
                .delete("/user/{id}", userId);
    }

    public Response getTags() {
        return baseRequest()
                .get("/tag");
    }

    private io.restassured.specification.RequestSpecification baseRequest() {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .header("app-id", appId)
                .accept(ContentType.JSON);
    }
}
