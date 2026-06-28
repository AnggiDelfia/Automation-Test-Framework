package com.example.framework.web.steps;

import com.example.framework.web.pages.CartPage;
import com.example.framework.web.pages.HomePage;
import com.example.framework.web.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoblazeWebSteps {
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String selectedProduct;

    @Given("I am on the Demoblaze home page")
    public void iAmOnTheDemoblazeHomePage() {
        homePage = new HomePage(WebHooks.driver());
        homePage.open();
    }

    @Then("I should see product cards")
    public void iShouldSeeProductCards() {
        assertThat(homePage.hasProducts()).isTrue();
    }

    @When("I open product {string}")
    public void iOpenProduct(String productName) {
        selectedProduct = productName;
        homePage.openProduct(productName);
        productPage = new ProductPage(WebHooks.driver());
    }

    @Then("the product detail page should show {string}")
    public void theProductDetailPageShouldShow(String expectedProductName) {
        assertThat(productPage.productTitle()).isEqualTo(expectedProductName);
    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        productPage.addToCart();
    }

    @When("I open the cart page")
    public void iOpenTheCartPage() {
        cartPage = homePage.openCart();
    }

    @Then("the cart should contain the selected product")
    public void theCartShouldContainTheSelectedProduct() {
        assertThat(cartPage.containsProduct(selectedProduct)).isTrue();
    }
}
