package PomPages;

import lombok.Getter;
import org.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminPages extends BaseClass {

    @FindBy(xpath = "//i[@class='oxd-icon bi-plus oxd-button-icon']")
    private WebElement addButton;
    @FindBy(xpath = "//h6[text()='Admin']")
    private WebElement adminPageTitle;
    @FindBy(xpath = "//h6[text()='Add User']")
    private WebElement addUserTitle;
    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    private WebElement userRoleDropdown;
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employNametextField;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    private WebElement statusDropDown;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextField;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement confirmPasswordTextField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[text()='Admin']")
    private WebElement adminoptionsInDropdown;
    @FindBy(xpath = "//div[text()='Enabled']")
    private WebElement enabledOptionsInDropDown;
    @FindBy(xpath = "((//div[@class='oxd-table-card'])[last()]//descendant::div[@role='cell'])[2]")
    private WebElement lastEntryUserName;
    @FindBy(xpath = "((//div[@class='oxd-table-card'])[last()]//descendant::div[@role='cell'])[last()]//descendant::button[@type='button']")
    private WebElement lastEntrydelete;
    @FindBy(xpath = "((//div[@class='oxd-table-card'])[last()]//descendant::div[@role='cell'])[last()]//descendant::button[@type='button']")
    private WebElement delete;
    @FindBy(xpath = "//p[text()='Successfully Deleted']")
    private WebElement deleteSuccesfullyMessage;
    @FindBy(xpath = "//i[@class='oxd-icon bi-trash oxd-button-icon']")
    private WebElement deleteConfirmationButton;
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    private WebElement searchtextField;
    @FindBy(xpath = "//span[text()='No Records Found']")
    private WebElement noRecordFoundTitle;





    public AdminPages(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    public void clickOnAddbutton(){
        getAddButton().click();
    }
    public void clickOnUserRoleDropdown(){
        getUserRoleDropdown().click();
    }

    public void createAUser() throws InterruptedException {

        seleniumUtility.waitForElementVisible(driver,getAdminPageTitle());
        clickOnAddbutton();
        seleniumUtility.waitForElementVisible(driver,getAddUserTitle());
        clickOnUserRoleDropdown();
        Thread.sleep(3000);
        seleniumUtility.waitForElementVisible(driver,getAdminoptionsInDropdown());
        getAdminoptionsInDropdown().click();
        getEmployNametextField().sendKeys("A");
        Thread.sleep(5000);
       // getStatusDropDown().click();
        //getEnabledOptionsInDropDown().click();
        getPasswordTextField().sendKeys("Test@1234");
        getConfirmPasswordTextField().sendKeys("Test@1234");
        getSaveButton().click();






    }
}


