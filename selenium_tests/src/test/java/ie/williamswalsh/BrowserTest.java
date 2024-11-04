package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowserTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        Browser browserSelection = Browser.valueOf(System.getProperty("browser")); //Browser.FIREFOX; // firefox / edge

        switch (browserSelection) {
            case CHROME -> {
//                System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
//                System.setProperty("webdriver.gecko.driver", "/Users/legoman/code/selenium/drivers/geckodriver");
                driver = new FirefoxDriver();
            }
            case EDGE -> {
//                System.setProperty("webdriver.edge.driver", "/Users/legoman/code/selenium/drivers/edge_driver_m1_mac");
                driver = new EdgeDriver();
            }
            default -> throw new RuntimeException("Browser selection invalid: " + browserSelection);
        }
    }

    @Test
    void actionTests() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement suggestiveDropdown = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        suggestiveDropdown.sendKeys("Ireland");
        Thread.sleep(500);
        String arrowDown = Keys.chord(Keys.ARROW_DOWN);
        String enter = Keys.chord(Keys.ENTER);
        suggestiveDropdown.sendKeys(arrowDown);
        suggestiveDropdown.sendKeys(arrowDown);
        suggestiveDropdown.sendKeys(enter);

        assertEquals("Ireland", suggestiveDropdown.getAttribute("value"));
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}

