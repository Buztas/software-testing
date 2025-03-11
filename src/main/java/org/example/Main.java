package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        final int[] index = {0};
        final String[][] extractedFirstElement = {{""}};

        // Pre-conditions
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();

        // Ensure at least 5 items are displayed in 'Featured Products'
        WebElement productGrid = driver.findElement(By.xpath("//div[@class='product-grid home-page-product-grid']"));
        final List<WebElement> itemBoxes = productGrid.findElements(By.xpath(".//div[@class='item-box']"));
        if (itemBoxes.size() < 5) {
            throw new Exception("Test Failed: Less than 5 items found in the Featured Products list. Found: " + itemBoxes.size());
        }

        //Test-steps

        // Verify "Compare products list" button is visible and enabled
        WebElement compareButton = driver.findElement(By.xpath("//a[@href='/compareproducts']"));
        if (!compareButton.isDisplayed() || !compareButton.isEnabled()) {
            throw new Exception("Test Failed: 'Compare products' button is not visible or enabled.");
        }

        // 2 - 5) Adding 4 items to "Compare Products" list
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                //6) Verify that the "Compare products" list contains 4 items
                if (index[0] < 4) {
                    try {
                        List<WebElement> freshItemBoxes = driver.findElements(
                                By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='item-box']"));

                        if (index[0] == 0) {
                        // 7) Saving the title of the first item
                            extractedFirstElement[0] = new String[]{
                                    freshItemBoxes.get(index[0]).findElement(By.xpath(".//div[@class='details']/h2[@class='product-title']/a")).getText()
                            };
                        }
                        freshItemBoxes.get(index[0]).findElement(By.xpath(".//div[@class='picture']")).click();// Click product

                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-2 add-to-compare-list-button']"))).click(); // Add to compare
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Compare products']")));
                        driver.findElement(By.xpath("//a[@href = '/']")).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-grid home-page-product-grid']")));
                        index[0]++;
                        return false;
                    } catch (Exception e) {
                        System.out.println("Error while adding item " + index[0] + ": " + e.getMessage());
                        driver.quit();
                    }
                }
                return true;
            }
        });

        // 8) Navigate to the home page, click the 5th item in the "Featured products" list and add it to "Compare products" list
        driver.findElement(By.xpath("//a[@href = '/']")).click();
        var firstElement = extractedFirstElement[0][0];
        System.out.println("firstElement: " + firstElement);
        List<WebElement> refreshedItemBoxes = driver.findElements(By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='item-box']")); // Re-fetch elements
        ++index[0];
        refreshedItemBoxes.get(index[0]).findElement(By.xpath(".//div[@class='picture']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-2 add-to-compare-list-button']"))).click();
        List<String> productTitles = driver.findElements(By.xpath("//tr[@class='product-name']/td/a")).stream()
                .map(WebElement::getText)
                .toList();

        // 9) Confirm that the first added item is not in the list
        if (productTitles.contains(firstElement)) {
            throw new Exception("First item is still in the list of products");
        }

        System.out.println("Item successfully removed");
        driver.quit();
    }
}