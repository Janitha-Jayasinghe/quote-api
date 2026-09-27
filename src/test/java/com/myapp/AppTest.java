package com.myapp;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void testQuoteEndpoint() throws InterruptedException {
        // 1. Start the API in a background thread
        Thread serverThread = new Thread(() -> {
            try {
                App.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        // 2. Wait for server to be ready
        Thread.sleep(2500);

        // 3. Launch headless Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            // Test root endpoint
            driver.get("http://localhost:8080/");
            String rootPage = driver.getPageSource();
            System.out.println("ROOT: " + rootPage);

            assertTrue("Response should contain student_id",
                rootPage.contains("student_id"));
            assertTrue("Response should contain message",
                rootPage.contains("message"));

            // Test health endpoint
            driver.get("http://localhost:8080/health");
            String healthPage = driver.getPageSource();
            System.out.println("HEALTH: " + healthPage);

            assertTrue("Health should return ok",
                healthPage.contains("ok"));

        } finally {
            driver.quit();
        }
    }
}