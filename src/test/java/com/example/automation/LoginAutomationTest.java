package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver-win64\\chromedriver.exe");

        // Add ChromeOptions to handle specific arguments
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Instantiate the WebDriver with options
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://www.saucedemo.com");

            // Create a WebDriverWait instance
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate elements on the page
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Interact with the elements
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Wait for the next page to load
            wait.until(ExpectedConditions.titleIs("Swag Labs"));

            // Validate the login result
            String expectedTitle = "Swag Labs";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle, "The login process failed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
