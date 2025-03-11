package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public abstract class BaseTest {
    public static String username;
    public static String password;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeAll
    public static void setUpUser() {
        UserCreation.createUser();
        username = UserCreation.getUsername();
        password = UserCreation.getPassword();
    }


//    @BeforeEach
//    public void setUp() throws IOException {
//        var userDataDir = Files.createTempDirectory("chrome-profile-");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath().toString());
//        driver = new ChromeDriver(options);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//    }

    @BeforeEach
    public void setUp() throws IOException {
        // Create a unique temporary directory for Chrome
        var userDataDir = Files.createTempDirectory("chrome-test-");

        ChromeOptions options = new ChromeOptions();
        // Add the user data directory option
        options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath().toString());
        // Keep your existing options
        options.addArguments("--headless=new");  // New headless mode (more stable)
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*"); // Fix certain permission issues

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}