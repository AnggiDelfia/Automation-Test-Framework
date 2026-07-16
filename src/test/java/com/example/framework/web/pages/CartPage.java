package com.example.framework.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By CART_ROWS = By.cssSelector("#tbodyid tr");
    private static final By PLACE_ORDER_BUTTON = By.cssSelector("button.btn.btn-success");
    private static final By NAME_INPUT = By.id("name");
    private static final By COUNTRY_INPUT = By.id("country");
    private static final By CITY_INPUT = By.id("city");
    private static final By CARD_INPUT = By.id("card");
    private static final By MONTH_INPUT = By.id("month");
    private static final By YEAR_INPUT = By.id("year");
    private static final By PURCHASE_BUTTON = By.xpath("//button[normalize-space()='Purchase']");
    private static final By PURCHASE_CONFIRMATION_TITLE = By.cssSelector(".sweet-alert h2");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsProduct(String productName) {
        wait.until(driver -> driver.findElements(CART_ROWS).size() > 0);
        return driver.findElements(By.xpath("//tbody[@id='tbodyid']//td[normalize-space()='" + productName + "']")).size() > 0;
    }

    public int productCount() {
        wait.until(driver -> driver.findElements(CART_ROWS).size() > 0);
        return driver.findElements(CART_ROWS).size();
    }

    public void placeOrder() {
        clickable(PLACE_ORDER_BUTTON).click();
    }

    public void completeCheckout(String name, String country, String city, String card, String month, String year) {
        visible(NAME_INPUT).sendKeys(name);
        visible(COUNTRY_INPUT).sendKeys(country);
        visible(CITY_INPUT).sendKeys(city);
        visible(CARD_INPUT).sendKeys(card);
        visible(MONTH_INPUT).sendKeys(month);
        visible(YEAR_INPUT).sendKeys(year);
        clickable(PURCHASE_BUTTON).click();
    }

    public String purchaseConfirmationTitle() {
        return visible(PURCHASE_CONFIRMATION_TITLE).getText();
    }
}
