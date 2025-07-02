package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class practice4 {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void startGrid(){
       runBatchFile(".\\dockerComposeUp.bat");
    }
    @AfterClass
    public void stopGrid(){
        runBatchFile(".\\dockerComposeDown.bat");
    }
    @BeforeMethod
    @Parameters({"browser"})
    public void login(String browser) throws MalformedURLException {
        MutableCapabilities options;
        if(browser.equalsIgnoreCase("Chrome")){
         options=new ChromeOptions();
        } else if (browser.equalsIgnoreCase("Firefox")) {
             options=new FirefoxOptions();
        }else{
             options=new ChromeOptions();
        }
        driver=new RemoteWebDriver(new URL("http://localhost:4444"),options);
        // driver=new RemoteWebDriver(options);
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1080,720));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));

    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        //Thread.sleep(5000);
        driver.quit();

    }
    @Test
    public void SelectRadioButton(){
        WebElement element=driver.findElement(By.xpath("//input[@value='radio1']"));
        element.click();
        Assert.assertTrue(element.isSelected(),"Radio button is not selected");
    }
    @Test
    public void autoSuggestion()  {
        driver.findElement(By.id("autocomplete")).sendKeys("United");
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ui-menu-item-wrapper']")));
        for(WebElement country:elements){
            System.out.println(country.getText());
            if(country.getText().equals("United States (USA)"))
                country.click();
        }
    }
    @Test
    public void selectDropDown() throws InterruptedException {
        Select select=new Select(driver.findElement(By.id("dropdown-class-example")));
        select.selectByValue("option1");
        Thread.sleep(3000);
        select.selectByIndex(2);
        Thread.sleep(3000);
        select.selectByVisibleText("Option3");
        List<WebElement> options = select.getOptions();
        for(WebElement element:options)
            System.out.println(element.getText());

    }
    @Test
    public void checkBoxSelect(){

        WebElement checkBox1 = driver.findElement(By.id("checkBoxOption1"));
        WebElement checkBox2 = driver.findElement(By.id("checkBoxOption2"));
        Assert.assertFalse(checkBox1.isSelected(),"CheckBox is selected");
        checkBox1.click();
        checkBox2.click();
        Assert.assertTrue(checkBox1.isSelected(),"Checkbox1 is not selected");
        Assert.assertTrue(checkBox1.isSelected(),"Checkbox2 is not selected");
    }
    @Test
    public void SwitchToWindowTesting() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.id("openwindow")).click();
        Set<String> allWindow = driver.getWindowHandles();
        for(String id:allWindow){
            if(!parentWindow.equals(id))
                driver.switchTo().window(id);
        }
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Access all our Courses']")).click();
        Thread.sleep(5000);
        driver.switchTo().window(parentWindow);
        //driver.switchTo().defaultContent();
        driver.findElement(By.id("opentab")).click();
        Thread.sleep(3000);
    }
    @Test
    public void SwitchToTabTesting() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.id("opentab")).click();

        Set<String> allWindow = driver.getWindowHandles();
        for(String id:allWindow){
            if(!parentWindow.equals(id))
                driver.switchTo().window(id);
        }
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Access all our Courses']")).click();
        Thread.sleep(5000);
        driver.close();
        driver.switchTo().window(parentWindow);
        //driver.switchTo().defaultContent();
        driver.findElement(By.id("opentab")).click();
        Thread.sleep(3000);
    }
    @Test
    public void alertPopup(){
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        driver.findElement(By.id("confirmbtn")).click();
        Alert alert2 = driver.switchTo().alert();
        alert2.dismiss();
        driver.findElement(By.id("confirmbtn")).click();
        alert2.accept();
    }
    @Test
    public void webTable(){
        JavascriptExecutor js=(JavascriptExecutor)driver;

        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//table[@id='product']//tbody/tr//td[text()='Appium (Selenium) - Mobile Automation Testing from Scratch']//following-sibling::td")));
        String mark = driver.findElement(By.xpath("//table[@id='product']//tbody/tr//td[text()='Appium (Selenium) - Mobile Automation Testing from Scratch']//following-sibling::td")).getText();
        System.out.println(mark);
        List<WebElement> entry = driver.findElements(By.xpath("//table[@id='product' and @name='courses']//tbody/tr"));
        for(int i=2;i<entry.size();i++){
            System.out.print(driver.findElement(By.xpath("//table[@id='product' and @name='courses']//tbody//tr["+i+"]//td[1]")).getText()+"||");
            System.out.print(driver.findElement(By.xpath("//table[@id='product' and @name='courses']//tbody//tr["+i+"]//td[2]")).getText()+"||");
            System.out.println(driver.findElement(By.xpath("//table[@id='product' and @name='courses']//tbody//tr["+i+"]//td[3]")).getText());

        }

    }
    @Test
    public void webTableStaticheader() throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor)driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",driver.findElement(By.xpath("//table[@id='product']//tbody/tr//td[text()='Appium (Selenium) - Mobile Automation Testing from Scratch']//following-sibling::td")));
        //String mark = driver.findElement(By.xpath("//table[@id='product']//tbody/tr//td[text()='Appium (Selenium) - Mobile Automation Testing from Scratch']//following-sibling::td")).getText();
        //System.out.println(mark);
        //Thread.sleep(3000);
        List<WebElement> entry = driver.findElements(By.xpath("(//table[@id='product'])[2]//tbody//tr"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",driver.findElement(By.xpath("(//table[@id='product'])[2]//tbody//tr[9]")));

        for(int i=1;i<entry.size();i++){
            System.out.print(driver.findElement(By.xpath("(//table[@id='product'])[2]//tbody//tr["+i+"]//td[1]")).getText()+"||");
            System.out.print(driver.findElement(By.xpath("(//table[@id='product'])[2]//tbody//tr["+i+"]//td[2]")).getText()+"||");
            System.out.print(driver.findElement(By.xpath("(//table[@id='product'])[2]//tbody//tr["+i+"]//td[3]")).getText()+"||");
            System.out.println(driver.findElement(By.xpath("(//table[@id='product'])[2]//tbody//tr["+i+"]//td[4]")).getText());
        }

    }
    @Test
    public void elementdisplayedVerified() throws InterruptedException {
        JavascriptExecutor ja=(JavascriptExecutor)driver;
        ja.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",driver.findElement(By.id("hide-textbox")));
        //Thread.sleep(3000);
        By element = By.id("displayed-text");
        System.out.println(driver.findElement(element).isDisplayed());
        driver.findElement(By.id("hide-textbox")).click();
        Thread.sleep(1000);
        System.out.println(driver.findElement(element).isDisplayed());
        driver.findElement(By.id("show-textbox")).click();
        Thread.sleep(1000);
        System.out.println(driver.findElement(element).isDisplayed());
    }
    @Test
    public void mouseHoverTesting() throws InterruptedException {
        WebElement mouseHoverElement = driver.findElement(By.id("mousehover"));
        JavascriptExecutor ja=(JavascriptExecutor)driver;
        ja.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",mouseHoverElement);
//Thread.sleep(2000);
        Actions a=new Actions(driver);
        a.moveToElement(mouseHoverElement).perform();
        driver.findElement(By.xpath("//a[text()='Reload']")).click();
    }

    public void runBatchFile(String filePath){
        try {
            Process process = Runtime.getRuntime().exec(filePath);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
