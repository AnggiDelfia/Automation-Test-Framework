@web
Feature: Demoblaze shopping flow

  Scenario: Visitor can view a product and add it to the cart
    Given I am on the Demoblaze home page
    Then I should see product cards
    When I open product "Samsung galaxy s6"
    Then the product detail page should show "Samsung galaxy s6"
    And I add the product to the cart
    When I open the cart page
    Then the cart should contain the selected product
