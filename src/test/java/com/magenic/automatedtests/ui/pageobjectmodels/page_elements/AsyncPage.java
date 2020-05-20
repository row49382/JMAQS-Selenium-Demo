package com.magenic.automatedtests.ui.pageobjectmodels.page_elements;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class AsyncPage extends BaseSeleniumPageModel {
    public LazyWebElement getAsyncPageIdentifier() {
        return this.getLazyElement(By.cssSelector("#LoadingLabel"));
    }

    public LazyWebElement getLazyLoadingDiv() {
        return this.getLazyElement(By.cssSelector("#loading-div"));
    }

    public LazyWebElement getSelectElement() {
        return this.getLazyElement(By.cssSelector("select#Selector"));
    }

    public AsyncPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    public Select getSelect() {
        return new Select(this.getSelectElement().getRawVisibleElement());
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getAsyncPageIdentifier().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }

    public boolean didAsyncLoadingDivDisappear() {
        return UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver())
                .waitUntilAttributeTextEquals(this.getLazyLoadingDiv().getBy(), "style", "display: none;");
    }
}
