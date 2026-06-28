package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By CART_ROWS = By.cssSelector("#tbodyid tr");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsProduct(String productName) {
        wait.until(driver -> driver.findElements(CART_ROWS).size() > 0);
        return driver.findElements(By.xpath("//tbody[@id='tbodyid']//td[normalize-space()='" + productName + "']")).size() > 0;
    }
}
