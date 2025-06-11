package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class practice {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void login(){
         driver=new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
         wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }
    @Test
    public void m1() throws InterruptedException {

        //Scroll to end of page
        JavascriptExecutor js=(JavascriptExecutor)driver;
        //js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        //Scroll by index
        //js.executeScript("window.scrollTo(0,500);");
        //Scroll to Element
        WebElement ScrollToElement=driver.findElement(By.xpath("//p[text()='Employees on Leave Today']"));
        //js.executeScript("arguments[0].scrollIntoView(true);",ScrollToElement);
        //Scroll to element with smooth scrolling
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ScrollToElement);
        Thread.sleep(5000);
        driver.quit();
    }
    @Test
    public void dropDown() throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor)driver;

        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[text()='User Management']"))));
       // WebElement dropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]"));
         WebElement dropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[2]"));
//wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        // Select s=new Select(dropdown);
        //Thread.sleep(5000);
        //js.executeScript("arguments[0].click();", dropdown);
        dropdown.click();
       // WebElement EnableOption=driver.findElement(By.xpath(""));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enabled']")));
        driver.findElement(By.xpath("//span[text()='Enabled']")).click();
        Thread.sleep(5000);

    }
}
