package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserCreation {

    public static void createUser() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[@href='/login']")).click();

        driver.findElement(By.xpath("//input[@class='button-1 register-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"gender-male\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys("Johnny");
        driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("Silverhand");
        driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("john.silverhand@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys("123456");

        driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-1 register-continue-button']")));
            driver.quit();
        } catch (Exception e) {
            //User exists, so silently failing
            driver.quit();
        }
    }
}