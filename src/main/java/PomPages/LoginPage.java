package PomPages;

import lombok.Getter;
import org.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends BaseClass {

    @FindBy(name = "username")
    private WebElement userNameTextField;
    @FindBy(name = "password")
    private WebElement passwordTextField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    @FindBy(xpath = "//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']")
    private WebElement loginPageTitle;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    public void enterUsername(String username) {
        userNameTextField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordTextField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getLoginPageTitleText() {
        return loginPageTitle.getText();
    }
    public void loginToApplication(String userName,String password){

        seleniumUtility.waitForElementVisible(driver,getLoginPageTitle());
        enterUsername(userName);
        enterPassword(password);
        clickLoginButton();
    }
}


