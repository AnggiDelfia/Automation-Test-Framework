package com.example.framework.api.steps;

import com.example.framework.api.clients.DummyApiClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TagApiSteps {
    private final DummyApiClient dummyApiClient = new DummyApiClient();
    private Response response;

    @When("I request the list of tags")
    public void iRequestTheListOfTags() {
        response = dummyApiClient.getTags();
    }

    @And("the tags response should contain at least one tag")
    public void theTagsResponseShouldContainAtLeastOneTag() {
        List<String> tags = response.jsonPath().getList("data");
        assertThat(tags).isNotEmpty();
    }

    @And("the tags API response status code should be {int}")
    public void theTagsApiResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }
}
