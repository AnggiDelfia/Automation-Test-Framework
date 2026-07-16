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

  Scenario: Visitor can filter products by laptop category
    Given I am on the Demoblaze home page
    When I open category "Laptops"
    Then I should see product "Sony vaio i5"

  Scenario: Visitor can view product detail and price
    Given I am on the Demoblaze home page
    When I open product "Nokia lumia 1520"
    Then the product detail page should show "Nokia lumia 1520"
    And the product price should be visible

  Scenario: Visitor can send a contact message
    Given I am on the Demoblaze home page
    When I send a contact message
    Then the contact message should be accepted

  Scenario: Visitor can complete checkout successfully
    Given I am on the Demoblaze home page
    When I open product "Samsung galaxy s6"
    And I add the product to the cart
    And I return to the home page
    When I open product "Nokia lumia 1520"
    And I add the product to the cart
    When I open the cart page
    Then the cart should contain 2 products
    When I place the order
    And I complete the checkout form
    Then the purchase should be successful
