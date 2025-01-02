package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "Z:/chromedriver-win64/chromedriver-win64");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox");

        WebDriver driver = null;

        try {
            driver = new ChromeDriver(options);

            // Navigate to the login page
            driver.get("https://example.com/login");

            // Locate the username and password fields, and login button
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Perform login
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Validate successful login by checking the page title
            String expectedTitle = "Dashboard";  // Adjust as per your application's dashboard title
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);

        } catch (NoSuchElementException e) {
            // Handle case where elements are not found
            System.err.println("Element not found: " + e.getMessage());
        } catch (TimeoutException e) {
            // Handle timeout issues (e.g., waiting for elements)
            System.err.println("Timeout while waiting for elements: " + e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser, even if an error occurred
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
