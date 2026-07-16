package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By PRODUCT_CARDS = By.cssSelector("#tbodyid .card-title a");
    private static final By CART_LINK = By.id("cartur");
    private static final By CONTACT_LINK = By.cssSelector("a[data-target='#exampleModal']");
    private static final By CONTACT_EMAIL_INPUT = By.id("recipient-email");
    private static final By CONTACT_NAME_INPUT = By.id("recipient-name");
    private static final By CONTACT_MESSAGE_INPUT = By.id("message-text");
    private static final By SEND_MESSAGE_BUTTON = By.xpath("//button[normalize-space()='Send message']");

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

    public void openCategory(String categoryName) {
        clickable(By.xpath("//a[@id='itemc' and normalize-space()='" + categoryName + "']")).click();
    }

    public boolean containsProduct(String productName) {
        return wait.until(driver -> driver.findElements(
                By.xpath("//a[contains(@class,'hrefch') and normalize-space()='" + productName + "']")).size() > 0);
    }

    public void sendContactMessage(String email, String name, String message) {
        clickable(CONTACT_LINK).click();
        visible(CONTACT_EMAIL_INPUT).sendKeys(email);
        visible(CONTACT_NAME_INPUT).sendKeys(name);
        visible(CONTACT_MESSAGE_INPUT).sendKeys(message);
        clickable(SEND_MESSAGE_BUTTON).click();
    }

    public CartPage openCart() {
        clickable(CART_LINK).click();
        return new CartPage(driver);
    }
}
