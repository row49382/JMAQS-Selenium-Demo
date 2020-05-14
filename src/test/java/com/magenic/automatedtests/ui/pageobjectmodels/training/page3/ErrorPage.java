package com.magenic.automatedtests.ui.pageobjectmodels.training.page3;

import com.magenic.automatedtests.ui.pageobjectmodels.BasePage;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import org.openqa.selenium.By;

public class ErrorPage extends BasePage {
    private By errorPageLocator = By.cssSelector("body > h1");

    public ErrorPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        return this.testObject.getWebDriver().findElement(this.errorPageLocator)
                .getText()
                .equalsIgnoreCase("Page unavailable");
    }
}
