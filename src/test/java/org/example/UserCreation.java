package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Random;

public class UserCreation {

    private static String createdUsername;
    private static String createdPassword;

    public static void createUser() {
        try {
            // Create a unique temporary directory for Chrome
            var userDataDir = Files.createTempDirectory("chrome-user-creation-");

            // Set up ChromeOptions with the temporary directory
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath().toString());
            // You can also add headless mode if you don't need to see the browser
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            try {
                driver.get("https://demowebshop.tricentis.com/");
                driver.manage().window().maximize();

                var random = new Random();
                createdUsername = "john.silverhand" + random.nextInt() + "@gmail.com";
                createdPassword = "crazy" + random.nextInt() + "!";

                driver.findElement(By.xpath("//a[@href='/login']")).click();
                driver.findElement(By.xpath("//input[@class='button-1 register-button']")).click();

                driver.findElement(By.xpath("//*[@id=\"gender-male\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys("Johnny");
                driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("Silverhand");
                driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(createdUsername);
                driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(createdPassword);
                driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys(createdPassword);

                driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();

                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@class='button-1 register-continue-button']")));
                } catch (Exception e) {
                    //User exists, so silently failing
                }
            } finally {
                driver.quit();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temporary directory for Chrome", e);
        }
    }

    public static String getUsername() {
        return createdUsername;
    }

    public static String getPassword() {
        return createdPassword;
    }
}