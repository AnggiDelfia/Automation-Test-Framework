package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("a.btn.btn-success");
    private static final By HOME_LINK = By.cssSelector(".navbar-brand");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String productTitle() {
        return visible(By.cssSelector(".name")).getText();
    }

    public String productPrice() {
        return visible(By.cssSelector(".price-container")).getText();
    }

    public void addToCart() {
        clickable(ADD_TO_CART_BUTTON).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public HomePage backToHome() {
        clickable(HOME_LINK).click();
        return new HomePage(driver);
    }
}
