@api
Feature: DummyAPI tag endpoint

  Scenario: Get list of available tags
    When I request the list of tags
    Then the tags API response status code should be 200
    And the tags response should contain at least one tag
