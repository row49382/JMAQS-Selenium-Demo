package com.magenic.automatedtests.ui.pageobjectmodels.training.page2;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseHomePage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TrainingHomePage2 extends BaseHomePage {
    protected By howDoesItWorkPageTabLocator = By.cssSelector("input#HowWork");
    protected By asyncpageTabLocator = By.cssSelector("input#Async");
    protected By howDoesItWorkPageTabWindowLocator = By.cssSelector("input#HowWork");
    protected By asyncpageTabWindowLocator = By.cssSelector("#AsyncContent");
    protected By asyncpageSelectorLocator = By.cssSelector("select#Selector");

    protected TrainingHomePage2(SeleniumTestObject testObject) {
        super(testObject);
    }

    /**
     * Switches the tabs using the Training Tab View option provided
     * @param viewOption The name of the tab to switch to
     */
    @Override
    public void switchTabViews(TrainingTabView viewOption) throws Exception {
        switch (viewOption) {
            case HOME: {
                this.testObject.getWebDriver().findElement(this.homepageTabLocator).click();
                break;
            }
            case ABOUT: {
                this.testObject.getWebDriver().findElement(this.aboutpageTabLocator).click();
                break;
            }
            case ASYNC_PAGE: {
                this.testObject.getWebDriver().findElement(this.asyncpageTabLocator).click();
                break;
            }
            case HOW_IT_WORKS: {
                this.testObject.getWebDriver().findElement(this.howDoesItWorkPageTabLocator).click();
                break;
            }
            default :
                throw new Exception(
                        String.format("view option %s provided is not supported for this page", viewOption.toString()));
        }
    }

    /**
     * Checks if the Training Tab View provided is currently in view
     * @param viewOption The name of the tab to check if it's in view
     * @return If the tab requested is in view
     */
    @Override
    public boolean isTabInView(TrainingTabView viewOption) throws Exception {
        boolean isInView;

        switch (viewOption) {
            case HOME: {
                isInView = UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                        .waitUntilVisibleElement(this.homepageTabWindowLocator);
                break;
            }
            case ABOUT: {
                isInView = UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                        .waitUntilVisibleElement(this.aboutpageTabWindowLocator);
                break;
            }
            case ASYNC_PAGE: {
                isInView = UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                        .waitUntilVisibleElement(this.asyncpageTabWindowLocator);
                break;
            }
            case HOW_IT_WORKS: {
                isInView = UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                        .waitUntilVisibleElement(this.howDoesItWorkPageTabWindowLocator);
                break;
            }
            default :
                throw new Exception(
                        String.format("view option %s provided is not supported for this page", viewOption.toString()));
        }

        return isInView;
    }

    public Select getAsyncPageSelectOption() {
        return new Select(this.testObject.getWebDriver().findElement(this.asyncpageSelectorLocator));
    }
}
