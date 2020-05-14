package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

public abstract class BaseErrorPage extends BaseSeleniumPageModel {
    protected By errorIdentifierLocator;

    protected LazyWebElement getErrorIdentifier() {
        return this.getLazyElement(this.errorIdentifierLocator);
    }

    public BaseErrorPage(SeleniumTestObject testObject, By by) {
        super(testObject);
        this.errorIdentifierLocator = by;
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getErrorIdentifier().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }
}
