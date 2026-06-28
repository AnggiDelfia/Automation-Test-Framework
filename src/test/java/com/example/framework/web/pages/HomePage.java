package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By PRODUCT_CARDS = By.cssSelector("#tbodyid .card-title a");
    private static final By CART_LINK = By.id("cartur");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(System.getProperty("web.baseUrl", "https://www.demoblaze.com/"));
    }

    public boolean hasProducts() {
        return wait.until(driver -> driver.findElements(PRODUCT_CARDS).size() > 0);
    }

    public void openProduct(String productName) {
        clickable(By.xpath("//a[contains(@class,'hrefch') and normalize-space()='" + productName + "']")).click();
    }

    public CartPage openCart() {
        clickable(CART_LINK).click();
        return new CartPage(driver);
    }
}
