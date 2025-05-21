package org;

import PomPages.AdminPages;
import PomPages.DashBoardpages;
import PomPages.LoginPage;
import Utility.SeleniumUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    public SeleniumUtility seleniumUtility=new SeleniumUtility();
    public LoginPage lp;
    public DashBoardpages dashBoardpages;
    public AdminPages adminPages;
    public ExtentReports extent=new ExtentReports();
    public ExtentTest test;
    @BeforeSuite
    public void reportConfig(){
        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
//Branch Rakesh
        ExtentSparkReporter reporterType=new ExtentSparkReporter("extentReport.html");// HTML report path
        extent.attachReporter(reporterType);
    }

    @BeforeMethod
    public void startBrowser(){
         driver=new ChromeDriver();
        driver.manage().window().maximize();
        //driver.
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        lp=new LoginPage(driver);
        dashBoardpages=new DashBoardpages(driver);
        adminPages=new AdminPages(driver);


    }
    //@BeforeMethod
    public void beforeMethod(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        lp=new LoginPage(driver);
        dashBoardpages=new DashBoardpages(driver);
        adminPages=new AdminPages(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){
        driver.quit();
    }
}
