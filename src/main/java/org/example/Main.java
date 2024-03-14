package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open Instagram
        driver.get("https://www.instagram.com/");

        // Log in to Instagram (replace 'username' and 'password' with your credentials)
        driver.findElement(By.name("username")).sendKeys("haloasadi");
        driver.findElement(By.name("password")).sendKeys("asa0220kg");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for the page to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find the post you want to comment on and click on it
        driver.get("https://www.instagram.com/p/C4Ca2qPolEM/?igsh=cTFkejhlamxyaHlv");

        // Wait for the page to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Loop 1500 times and add your comment
        for (int i = 0; i < 15; i++) {
            // Retry mechanism for locating the comment input field
            WebElement commentInput = null;
            int attempts = 0;
            while (attempts < 3) {
                try {
                    commentInput = driver.findElement(By.cssSelector("textarea[placeholder='Add a comment…']"));
                    break;
                } catch (StaleElementReferenceException e) {
                    // Retry locating the element
                }
                attempts++;
            }

            if (commentInput != null) {
                commentInput.sendKeys("Hello there");

                // Find the 'Post' button and click on it
                WebElement postButton = driver.findElement(By.xpath("//button[contains(text(),'Post')]"));
                postButton.click();

                // Wait for a short while before adding the next comment
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        driver.quit();
    }
}
