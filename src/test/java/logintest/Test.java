package logintest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        // WebDriver initialization
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        //usernames
        String[] usernames = {
            "standard_user",
            "locked_out_user",
            "problem_user",
            "performance_glitch_user"
        };
        
        //Login Page
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().window().maximize();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        for (String username : usernames) {
            // Clear previous inputs (if any)
            driver.findElement(By.id("user-name")).clear();
            driver.findElement(By.id("password")).clear();

            driver.findElement(By.id("user-name")).sendKeys(username);  
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000);

            if (driver.getCurrentUrl().contains("inventory")) {
                System.out.println("Login successful for user: " + username);
                
                driver.navigate().back();
                Thread.sleep(1000); 
            } else {
                System.out.println("Login failed for user: " + username);
            }
        }
        
        driver.quit();
    }
}
