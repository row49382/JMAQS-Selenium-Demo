package com.magenic.automatedtests.ui.pageobjectmodels;

import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;

public abstract class BasePage {
    protected SeleniumTestObject testObject;

    protected BasePage(SeleniumTestObject testObject) {
        this.testObject = testObject;
    }

    /**
     * Verifies if the page model created loaded
     * @return If the page model loaded
     */
    public abstract boolean isPageLoaded() throws TimeoutException, InterruptedException;
}
