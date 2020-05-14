package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

public class BootstrapModalWindow extends BaseSeleniumPageModel {
    public enum CloseOption { X, CLOSE_BUTTON }

    public LazyWebElement getIdentifier() {
        return this.getLazyElement(By.cssSelector("#myModal"));
    }

    public LazyWebElement getCloseXButton() {
        return this.getLazyElement(By.cssSelector("div.modal-header > button.close"));
    }

    public LazyWebElement getCloseButton() {
        return this.getLazyElement(By.cssSelector("div.modal-footer > button.btn-default"));
    }

    public BootstrapModalWindow(SeleniumTestObject testObject) {
        super(testObject);
    }

    public void close(CloseOption closeOption) throws InterruptedException, TimeoutException, ExecutionFailedException {
        switch (closeOption) {
            case X: {
                this.getCloseXButton().click();
                break;
            }
            case CLOSE_BUTTON: {
                this.getCloseButton().click();
                break;
            }
        }

        UIWaitFactory.getWaitDriver(this.getWebDriver())
                .getWaitDriver().until((driver) -> driver.findElement(this.getIdentifier().getBy())
                .getAttribute("style")
                .contains("display: none;"));
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return UIWaitFactory.getWaitDriver(this.getWebDriver())
                    .getWaitDriver().until(
                    (driver) -> driver.findElement(this.getIdentifier().getBy())
                            .getAttribute("style")
                            .contains("display: block;"));
        }
        catch (Exception e) {
            return false;
        }
    }
}
