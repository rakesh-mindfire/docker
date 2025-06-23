package org.example;

import Utility.SeleniumUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Second {
    ExtentReports extent=new ExtentReports();
    ExtentTest test;
    @BeforeClass
    public void before(){
        ExtentSparkReporter reporterType=new ExtentSparkReporter("./reports/extentReport.html");// HTML report path
        extent.attachReporter(reporterType);
    }
    @AfterClass
    public void after(){
        extent.flush();
    }
    @Test
    public void testGoogleTitle() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage","--disable-web-security",
                "--disable-features=AllowInsecureLocalhost",
                "--allow-insecure-localhost");
        //FirefoxOptions options=new FirefoxOptions();

        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        Thread.sleep(10000);

        Assert.assertTrue(title.contains("Google"));

        driver.quit();
    }


   // @Test
    public void m3() throws Throwable {
        test = extent.createTest("TC_002", "Testing description");

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");

        WebDriver driver=new ChromeDriver(options);
        test.log(Status.INFO,"Info 1");

        driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2000);
        test.log(Status.PASS,"Info 2");
        //driver.findElement(By.xpath("//div[text()='From']//following-sibling::div")).sendKeys("Bhubaneswar");
       // driver.findElement(By.xpath("//div[text()='To']//following-sibling::div")).sendKeys("Cuttack");

        //driver.findElement(By.xpath("//i[@class='icon___8505b0 icon icon-date_range']//parent::div")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollBy(0,250)", "");
       // for(int i=0;i<3;i++){
       //     driver.findElement(By.xpath("(//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[last()]")).click();
            //System.out.println(driver.findElement(By.xpath("//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[2]")).getText());
       // }
       // driver.findElement(By.xpath("//span[text()='1']")).click();
        driver.findElement(By.xpath("//button[text()='SEARCH BUSES']")).click();
        test.log(Status.INFO,"Info 3");
        Thread.sleep(5000);
        driver.quit();


        //String monthYearVal = driver.findElement(By.xpath("(//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[2]")).g
    }
}
