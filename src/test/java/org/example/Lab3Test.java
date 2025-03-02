package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab3Test extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"data1.txt", "data2.txt"})
    public void test1(String fileName) throws Exception {
        driver.get("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Digital downloads')]")).click();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String product;
            while ((product = br.readLine()) != null) {
                driver.findElement(By.xpath("//a[text()='" + product + "']/ancestor::div[@class='details']//input[@value='Add to cart']")).click();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + fileName, e);
        }

        driver.findElement(By.xpath("//a[text()='Shopping cart']")).click();
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        if (driver.findElement(By.xpath("//input[@id='BillingNewAddress_FirstName']")).isDisplayed()) {
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_City\"]")).sendKeys("Some city");
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]")).sendKeys("Some street");
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]")).sendKeys("12345");
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]/option[2]")).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"billing-buttons-container\"]/input"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment-info-buttons-container\"]/input"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/input"))).click();

        var text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title']/strong"))).getText();
        Assertions.assertEquals("Your order has been successfully processed!", text);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='button-2 order-completed-continue-button']"))).click();
    }
}