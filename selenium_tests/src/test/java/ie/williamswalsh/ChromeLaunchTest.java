package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeLaunchTest {

    private static WebDriver driver;

    private static WebDriverWait wait;

    private static final String TARGET_URL = "https://www.saucedemo.com/";

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Test
    void getUrlEndpoint() {
        driver.get(TARGET_URL);
    }

    @Test
    void getTitleOfWebpage() {
        driver.get(TARGET_URL);
        assertEquals("Swag Labs", driver.getTitle());
    }


    @Test
    void logIn_CorrectCredentials() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    void logIn_IncorrectCredentials() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("incorrect_password");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-container")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector()));

        // wait for error-message-container error to become visible
    }


//    Accepted usernames are:
//    standard_user
//            locked_out_user
//    problem_user
//            performance_glitch_user
//    error_user
//            visual_user
//    Password for all users:
//    secret_sauce


//    @Test
//    void checkForNoUrlRedirects() {
//        String correctUrl = "https://rahulshettyacademy.com/";
//        driver.get(correctUrl);
//        assertEquals(correctUrl, driver.getCurrentUrl());
//    }

    @AfterAll
    static void tearDown() {
        // Only closes the current window
        driver.close();
        // closes all associates windows/tabs
        driver.quit();
    }
}
