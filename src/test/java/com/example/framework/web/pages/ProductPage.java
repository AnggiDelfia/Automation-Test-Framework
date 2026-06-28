package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("a.btn.btn-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String productTitle() {
        return visible(By.cssSelector(".name")).getText();
    }

    public void addToCart() {
        clickable(ADD_TO_CART_BUTTON).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}
