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

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private static final String TARGET_URL = "https://www.saucedemo.com";
    private static final String BAD_CREDENTIALS_ERROR = "Epic sadface: Username and password do not match any user in this service";
    private static final String MISSING_USERNAME_ERROR = "Epic sadface: Username is required";
    private static final String MISSING_PASSWORD_ERROR = "Epic sadface: Password is required";
    private static final String LOCKED_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";

    private static final String INVENTORY_BROWSE_NO_CREDS_ERROR = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    private static WebDriver d;

    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
        wait = new WebDriverWait(d, Duration.ofSeconds(3));
    }

    @Test
    void getUrlEndpoint() {
        d.get(TARGET_URL);
    }

    @Test
    void getTitleOfWebpage() {
        d.get(TARGET_URL);
        assertEquals("Swag Labs", d.getTitle());
    }

    @Test
    void logIn_CorrectCredentials() {
        d.get(TARGET_URL);
        d.findElement(By.id("user-name")).sendKeys("standard_user");
        d.findElement(By.id("password")).sendKeys("secret_sauce");
        d.findElement(By.id("login-button")).click();

        assertEquals("Products", d.findElement(By.cssSelector("span.title")).getText());
    }

    @Test
    void logIn_IncorrectCredentials() {
        d.get(TARGET_URL);
        d.findElement(By.id("user-name")).sendKeys("standard_user");
        d.findElement(By.id("password")).sendKeys("incorrect_password");
        d.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(BAD_CREDENTIALS_ERROR, d.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_MissingUserName() {
        d.get(TARGET_URL);
        d.findElement(By.id("password")).sendKeys("secret_sauce");
        d.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(MISSING_USERNAME_ERROR, d.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_MissingPassword() {
        d.get(TARGET_URL);
        d.findElement(By.id("user-name")).sendKeys("standard_user");
        d.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(MISSING_PASSWORD_ERROR, d.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_LockedOutUser() {
        d.get(TARGET_URL);
        d.findElement(By.id("user-name")).sendKeys("locked_out_user");
        d.findElement(By.id("password")).sendKeys("secret_sauce");
        d.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));

        assertEquals(LOCKED_USER_ERROR, d.findElement(By.cssSelector("div.error-message-container")).getText());
    }

    @Test
    void logIn_ThenLogout() {
        d.get(TARGET_URL);
        d.findElement(By.id("user-name")).sendKeys("standard_user");
        d.findElement(By.id("password")).sendKeys("secret_sauce");
        d.findElement(By.id("login-button")).click();

        assertEquals("Products", d.findElement(By.cssSelector("span.title")).getText());

        d.findElement(By.cssSelector("div.bm-burger-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#logout_sidebar_link")));
        d.findElement(By.cssSelector("a#logout_sidebar_link")).click();

        // Assert that the login_container is on page
        assertNotNull(d.findElement(By.cssSelector("div.login_container")));
    }

    @Test
    void browseToInventoryWithNoCredentials() {
        d.get(TARGET_URL + "/inventory.html");

        // Assert that you are redirected to home page when accessing Inventory page without creds.
        assertEquals(TARGET_URL, d.getCurrentUrl());

        // Assert error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error-message-container")));
        assertEquals(INVENTORY_BROWSE_NO_CREDS_ERROR, d.findElement(By.cssSelector("div.error-message-container")).getText());
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

    @AfterAll
    static void tearDown() {
        // Only closes the current window
        d.close();
        // closes all associates windows/tabs
        d.quit();
    }
}
