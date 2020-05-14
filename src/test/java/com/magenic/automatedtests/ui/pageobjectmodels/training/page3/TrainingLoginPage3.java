package com.magenic.automatedtests.ui.pageobjectmodels.training.page3;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseLoginPage;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import org.openqa.selenium.By;

public class TrainingLoginPage3 extends BaseLoginPage {
    private By userNameLocator = By.cssSelector("#name");
    private By passwordLocator = By.cssSelector("#pw");

    public TrainingLoginPage3(SeleniumTestObject testObject) {
        super(testObject);
    }

    public TrainingHomePage3 loginSuccess(String username, String pass) {
        this.login(username, pass);
        return new TrainingHomePage3(this.testObject);
    }

    @Override
    public void login(String username, String pass) {
        this.testObject.getWebDriver().findElement(this.userNameLocator).sendKeys(username);
        this.testObject.getWebDriver().findElement(this.passwordLocator).sendKeys(pass);

        this.testObject.getWebDriver().findElement(this.loginButtonLocator).click();
    }
}
