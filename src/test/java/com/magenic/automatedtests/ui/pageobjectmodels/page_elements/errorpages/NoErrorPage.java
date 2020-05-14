package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.errorpages;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

public class NoErrorPage extends BaseSeleniumPageModel {
    public LazyWebElement getNoErrorPageIdentifierElement() {
        return this.getLazyElement(By.xpath("//body[text()='The page cannot be displayed because an internal server error has occurred.']"), "");
    }

    public NoErrorPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getNoErrorPageIdentifierElement().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }
}
