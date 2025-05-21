package PomPages;

import lombok.Getter;
import org.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class DashBoardpages extends BaseClass {

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardPageTitle;
    @FindBy(xpath = "//span[text()='Admin']\n")
    private WebElement adminMenu;

    public DashBoardpages(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    public void waitUntilDashboardPageLoads(){
        seleniumUtility.waitForElementVisible(driver,getDashboardPageTitle());
    }
}


