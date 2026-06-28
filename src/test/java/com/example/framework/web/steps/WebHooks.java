package com.example.framework.web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebHooks {
    private static WebDriver driver;

    @Before("@web")
    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1440,900");
        driver = new ChromeDriver(options);
    }

    @After("@web")
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver driver() {
        return driver;
    }
}
