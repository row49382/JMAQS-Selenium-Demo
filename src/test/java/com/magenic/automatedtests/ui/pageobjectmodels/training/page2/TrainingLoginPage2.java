package com.magenic.automatedtests.ui.pageobjectmodels.training.page2;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseLoginPage;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.TimeoutException;
import org.openqa.selenium.By;

public class TrainingLoginPage2 extends BaseLoginPage {
    private By trainingPageLocator = By.cssSelector("p[text='Login']");
    private By userNameLocator = By.cssSelector("#UserName");
    private By passwordLocator = By.cssSelector("#Password");
    private By loginButtonLocator = By.cssSelector("#Login");
    private By loginFailedTextLocator = By.cssSelector("#LoginError");

    public TrainingLoginPage2(SeleniumTestObject testObject) {
        super(testObject);
    }

    public TrainingHomePage2 loginSuccess(String username, String pass) {
        this.login(username, pass);
        return new TrainingHomePage2(this.testObject);
    }

    public String loginInvalid(String username, String pass) throws TimeoutException, InterruptedException {
        this.login(username, pass);
        this.waitForErrorMessage();
        return this.testObject.getWebDriver().findElement(loginFailedTextLocator).getText();
    }
}