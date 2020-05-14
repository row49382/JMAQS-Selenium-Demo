package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

public class DialogTwoWindow extends BaseSeleniumPageModel {
    public LazyWebElement getIdentifier() {
        return this.getLazyElement(By.cssSelector("#ui-id-1"));
    }

    public LazyWebElement getOkButton() {
        return this.getLazyElement(By.cssSelector("#body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button"));
    }

    public DialogTwoWindow(SeleniumTestObject testObject) {
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

    public void clickOk() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getOkButton().click();
    }
}
