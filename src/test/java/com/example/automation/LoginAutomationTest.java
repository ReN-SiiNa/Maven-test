package com.example.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAutomationTest {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        // Set up WebDriver using WebDriverManager (no need to specify path)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void testLogin() {
        try {
            // Navigate to the Sauce Demo login page
            driver.get("https://www.saucedemo.com");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Perform login using provided test credentials
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Validate successful login by checking the page title or a unique element
            WebElement inventoryPageTitle = driver.findElement(By.className("title"));
            String expectedTitle = "PRODUCTS";
            String actualTitle = inventoryPageTitle.getText();

            // Use case-insensitive comparison to validate title
            assertEquals(expectedTitle.toLowerCase(), actualTitle.toLowerCase(), "Login test failed: Title mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Login test failed due to an exception.");
        }
    }

    @Test
    void testWelcomeMessage() {
        // Create an instance of Application (not App)
        Application app = new Application();

        // Call the method from Application and check the result
        String result = app.welcomeMessage("Aryan");

        // Validate the result
        assertEquals("Hello, Aryan!", result, "The welcome message should be correct.");
    }

    @AfterAll
    static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
