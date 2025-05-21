package org.example;

import PomPages.LoginPage;
import org.BaseClass;
import org.testng.annotations.Test;

public class First extends BaseClass {
   // @Test
    public void m1() throws InterruptedException {
       // lp.getUserNameTextField().sendKeys("Admin");
       // lp.getPasswordTextField().sendKeys("admin123");
        //lp.getLoginButton().click();
        lp.loginToApplication("Admin","admin123");
        dashBoardpages.waitUntilDashboardPageLoads();
        dashBoardpages.getAdminMenu().click();
        adminPages.createAUser();
    }
   // @Test
    public void deleteAndSearch() throws InterruptedException {
        lp.loginToApplication("Admin","admin123");
        dashBoardpages.waitUntilDashboardPageLoads();
        dashBoardpages.getAdminMenu().click();
        seleniumUtility.scrollToLast(driver);
        String lastUserName=adminPages.getLastEntryUserName().getText();
        adminPages.getLastEntrydelete().click();
        Thread.sleep(3000);
        adminPages.getDeleteConfirmationButton().click();
        seleniumUtility.waitForElementVisible(driver,adminPages.getDeleteSuccesfullyMessage());
        adminPages.getDeleteSuccesfullyMessage().isDisplayed();
seleniumUtility.scrollToElement(driver,adminPages.getSearchtextField());
adminPages.getSearchtextField().sendKeys(lastUserName);
        seleniumUtility.scrollToElement(driver,adminPages.getSaveButton());

        Thread.sleep(3000);
        adminPages.getSaveButton().click();
        //Thread.sleep(3000);
        seleniumUtility.waitForElementVisible(driver,adminPages.getNoRecordFoundTitle());
        adminPages.getNoRecordFoundTitle().isDisplayed();
       // Thread.sleep(5000);



    }

}
