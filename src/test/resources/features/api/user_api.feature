@api
Feature: DummyAPI user endpoint

  Scenario: Create, get, update, and delete a user
    Given a valid user payload
    When I create the user using DummyAPI
    Then the API response status code should be 200
    And the user response should contain the created user data
    When I get the created user by id
    Then the API response status code should be 200
    And the returned user id should match the created user
    When I update the created user's last name to "Updated"
    Then the API response status code should be 200
    And the user response should contain last name "Updated"
    When I delete the created user
    Then the API response status code should be 200
    And the delete response should contain the deleted user id
