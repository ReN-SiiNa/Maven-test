package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver-win64\\chromedriver-win64");

        // Instantiate the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://www.saucedemo.com");

            // Locate elements on the page
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Interact with the elements
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Wait for page to load (a better approach would involve WebDriverWait)
            Thread.sleep(3000);

            // Validate the login result
            String expectedTitle = "Dashboard";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
