package com.example.framework.web.steps;

import com.example.framework.web.pages.CartPage;
import com.example.framework.web.pages.HomePage;
import com.example.framework.web.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @Then("the product price should be visible")
    public void theProductPriceShouldBeVisible() {
        assertThat(productPage.productPrice()).startsWith("$");
    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        productPage.addToCart();
    }

    @And("I return to the home page")
    public void iReturnToTheHomePage() {
        homePage = productPage.backToHome();
        assertThat(homePage.hasProducts()).isTrue();
    }

    @When("I open category {string}")
    public void iOpenCategory(String categoryName) {
        homePage.openCategory(categoryName);
    }

    @Then("I should see product {string}")
    public void iShouldSeeProduct(String productName) {
        assertThat(homePage.containsProduct(productName)).isTrue();
    }

    @When("I send a contact message")
    public void iSendAContactMessage() {
        homePage.sendContactMessage(
                "automation.tester@mail.com",
                "Automation Tester",
                "This message is created by an automation test.");
    }

    @Then("the contact message should be accepted")
    public void theContactMessageShouldBeAccepted() {
        WebDriverWait alertWait = new WebDriverWait(WebHooks.driver(), Duration.ofSeconds(10));
        String alertText = alertWait.until(ExpectedConditions.alertIsPresent()).getText();
        WebHooks.driver().switchTo().alert().accept();
        assertThat(alertText).isEqualTo("Thanks for the message!!");
    }

    @When("I open the cart page")
    public void iOpenTheCartPage() {
        cartPage = homePage.openCart();
    }

    @Then("the cart should contain the selected product")
    public void theCartShouldContainTheSelectedProduct() {
        assertThat(cartPage.containsProduct(selectedProduct)).isTrue();
    }

    @Then("the cart should contain {int} products")
    public void theCartShouldContainProducts(int expectedProductCount) {
        assertThat(cartPage.productCount()).isEqualTo(expectedProductCount);
    }

    @When("I place the order")
    public void iPlaceTheOrder() {
        cartPage.placeOrder();
    }

    @And("I complete the checkout form")
    public void iCompleteTheCheckoutForm() {
        cartPage.completeCheckout(
                "Automation Tester",
                "Indonesia",
                "Jakarta",
                "4111111111111111",
                "12",
                "2028");
    }

    @Then("the purchase should be successful")
    public void thePurchaseShouldBeSuccessful() {
        assertThat(cartPage.purchaseConfirmationTitle()).isEqualTo("Thank you for your purchase!");
    }
}
