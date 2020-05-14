package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.omg.CORBA.INTERNAL;
import org.openqa.selenium.By;

public class DialogOneWindow extends BaseSeleniumPageModel {
    public LazyWebElement getIdentifier() {
        return this.getLazyElement(By.cssSelector("#loading-div"));
    }

    public LazyWebElement getCloseButton() {
        return this.getLazyElement(By.cssSelector("button#CloseButtonShowDialog"));
    }

    public DialogOneWindow(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getIdentifier().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }

    public void close() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getCloseButton().click();
    }
}
