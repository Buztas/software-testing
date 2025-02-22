package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class la2 {
    //2.1 uzd
//    WebDriver driver = new ChromeDriver();
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://demoqa.com/");
//        driver.manage().window().maximize();
//        driver.findElement(By.xpath("//h5[text() = 'Widgets']/ancestor::div[@class = 'card-body']")).click();
//        driver.findElement(By.xpath("//span[@class = 'text' and text() = 'Progress Bar']")).click();
//        driver.findElement(By.xpath("//button[@id = 'startStopButton' and text() = 'Start']")).click();
//    var progressBar = driver.findElement(By.xpath("//div[contains(@class, 'progress-bar')]"));
//        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[contains(@class, 'progress-bar')]"), "aria-valuenow", "100"));
//        driver.findElement(By.xpath("//button[@id = 'resetButton' and text() = 'Reset']")).click();
//    var isProgressBarReset = Objects.equals(progressBar.getDomAttribute("aria-valuenow"), "0");
//        if(!isProgressBarReset) {
//        throw new Exception("The progress bar is not reset");
//    }
//
//    //2.2 uzd
//        driver.get("https://demoqa.com/");
////        driver.manage().window().maximize();
//        driver.findElement(By.xpath("//h5[text() = 'Elements']/ancestor::div[@class = 'card-body']")).click();
//        driver.findElement(By.xpath("//span[@class = 'text' and text() = 'Web Tables']")).click();
//        while(driver.findElement(By.xpath("//span[@class='-totalPages']")).getText().equals("1")) {
//        driver.findElement(By.xpath("//button[@id='addNewRecordButton' and text()='Add']")).click();
//        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Johnny");
//        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Silverhand");
//        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("johnny.silverhand@samurai.com");
//        driver.findElement(By.xpath("//input[@id='age']")).sendKeys("25");
//        driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("1000000");
//        driver.findElement(By.xpath("//input[@id='department']")).sendKeys("Rocker boy");
//        driver.findElement(By.xpath("//button[@id='submit']")).click();
//    }
//        driver.findElement(By.xpath("//button[@class='-btn' and text() = 'Next']")).click();
//        driver.findElement(By.xpath("//span[@id = 'delete-record-11']")).click();
//    var totalPages = driver.findElement(By.xpath("//span[@class='-totalPages' and text()='1']")).getText();
//        driver.findElement(By.xpath("//input[@aria-label='jump to page' and @value='2']")).click();
//        driver.findElement(By.xpath("//span[@class='-totalPages' and text()='1']")).click();
//    var currentPage = driver.findElement(By.xpath("//input[@aria-label='jump to page' and @value='1']")).getText();
//        if(!(currentPage.equals("1") && totalPages.equals("1"))) {
//        throw new Exception("Auto pagination upon deletion does not work");
//    }
//        driver.quit();
//}
}
