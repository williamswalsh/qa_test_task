package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private static final String TARGET_URL = "https://www.saucedemo.com/";
    private static final String BAD_CREDENTIALS_ERROR = "Epic sadface: Username and password do not match any user in this service";
    private static final String MISSING_USERNAME_ERROR = "Epic sadface: Username is required";
    private static final String MISSING_PASSWORD_ERROR = "Epic sadface: Password is required";
    private static final String LOCKED_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";



    private static WebDriver driver;

    private static WebDriverWait wait;

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

        assertEquals("Products", driver.findElement(By.cssSelector("span.title")).getText());
    }

    @Test
    void logIn_IncorrectCredentials() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("incorrect_password");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(BAD_CREDENTIALS_ERROR, driver.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_MissingUserName() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("password")).sendKeys("incorrect_password");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(MISSING_USERNAME_ERROR, driver.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_MissingPassword() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(MISSING_PASSWORD_ERROR, driver.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_LockedOutUser() {
        driver.get(TARGET_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(LOCKED_USER_ERROR, driver.findElement(By.cssSelector("div.error-message-container")).getText());
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
