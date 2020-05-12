package com.magenic.automatedtests.ui.pageobjectmodels.training.page1;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseHomePage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;

public class TrainingHomePage1 extends BaseHomePage {

    protected TrainingHomePage1(SeleniumTestObject testObject) {
        super(testObject);
    }

    public boolean isPageLoaded() {
        return UIWaitFactory.getWaitDriver(this.testObject.getWebDriver()).waitUntilVisibleElement(this.homepageTabLocator);
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
            default :
                throw new Exception(
                        String.format("view option %s provided is not supported for this page", viewOption.toString()));
        }

        return isInView;
    }
}
