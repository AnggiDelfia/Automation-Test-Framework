package com.example.framework.api.steps;

import com.example.framework.api.clients.DummyApiClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiSteps {
    private final DummyApiClient dummyApiClient = new DummyApiClient();
    private final Map<String, Object> userPayload = new HashMap<>();
    private Response response;
    private String createdUserId;

    @Given("a valid user payload")
    public void aValidUserPayload() {
        String uniqueSuffix = String.valueOf(Instant.now().toEpochMilli());
        userPayload.clear();
        userPayload.put("title", "mr");
        userPayload.put("firstName", "Automation");
        userPayload.put("lastName", "Tester");
        userPayload.put("email", "automation.tester." + uniqueSuffix + "@mail.com");
    }

    @When("I create the user using DummyAPI")
    public void iCreateTheUserUsingDummyApi() {
        response = dummyApiClient.createUser(userPayload);
        createdUserId = response.jsonPath().getString("id");
    }

    @Then("the API response status code should be {int}")
    public void theApiResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    @And("the user response should contain the created user data")
    public void theUserResponseShouldContainTheCreatedUserData() {
        assertThat(createdUserId).isNotBlank();
        assertThat(response.jsonPath().getString("firstName")).isEqualTo(userPayload.get("firstName"));
        assertThat(response.jsonPath().getString("lastName")).isEqualTo(userPayload.get("lastName"));
        assertThat(response.jsonPath().getString("email")).isEqualTo(userPayload.get("email"));
    }

    @When("I get the created user by id")
    public void iGetTheCreatedUserById() {
        response = dummyApiClient.getUserById(createdUserId);
    }

    @And("the returned user id should match the created user")
    public void theReturnedUserIdShouldMatchTheCreatedUser() {
        assertThat(response.jsonPath().getString("id")).isEqualTo(createdUserId);
    }

    @When("I update the created user's last name to {string}")
    public void iUpdateTheCreatedUsersLastNameTo(String lastName) {
        response = dummyApiClient.updateUser(createdUserId, Map.of("lastName", lastName));
    }

    @And("the user response should contain last name {string}")
    public void theUserResponseShouldContainLastName(String expectedLastName) {
        assertThat(response.jsonPath().getString("lastName")).isEqualTo(expectedLastName);
    }

    @When("I delete the created user")
    public void iDeleteTheCreatedUser() {
        response = dummyApiClient.deleteUser(createdUserId);
    }

    @And("the delete response should contain the deleted user id")
    public void theDeleteResponseShouldContainTheDeletedUserId() {
        assertThat(response.jsonPath().getString("id")).isEqualTo(createdUserId);
    }
}
