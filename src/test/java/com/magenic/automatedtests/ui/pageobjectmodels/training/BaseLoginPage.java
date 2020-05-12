package com.magenic.automatedtests.ui.pageobjectmodels.training;

import com.magenic.automatedtests.ui.pageobjectmodels.BasePage;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.TimeoutException;
import org.openqa.selenium.By;

public abstract class BaseLoginPage extends BasePage {
    protected By trainingLoginPageLocator = By.cssSelector("p[text='Login']");
    protected By userNameLocator = By.cssSelector("#UserName");
    protected By passwordLocator = By.cssSelector("#Password");
    protected By loginButtonLocator = By.cssSelector("#Login");
    protected By loginFailedTextLocator = By.cssSelector("#LoginError");

    protected BaseLoginPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        return UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                .waitUntilVisibleElement(this.trainingLoginPageLocator);
    }

    protected  void login(String username, String pass) {
        this.testObject.getWebDriver().findElement(this.userNameLocator).sendKeys(username);
        this.testObject.getWebDriver().findElement(this.passwordLocator).sendKeys(pass);

        this.testObject.getWebDriver().findElement(this.loginButtonLocator).click();
    }

    protected void waitForErrorMessage() throws TimeoutException, InterruptedException {
        GenericWait.waitFor(
                () -> !this.testObject.getWebDriver().findElement(this.loginFailedTextLocator)
                        .getText()
                        .isEmpty()
        );
    }
}
