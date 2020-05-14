package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.errorpages;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

public class ErrorPage extends BaseSeleniumPageModel {
    public LazyWebElement getErrorPageIdentifierElement() {
        return this.getLazyElement(By.xpath("//body[text()='The page cannot be displayed because an internal server error has occurred.']"));
    }

    public ErrorPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getErrorPageIdentifierElement().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }
}
