package com.magenic.automatedtests.ui.pageobjectmodels.training;

import com.magenic.automatedtests.ui.pageobjectmodels.BasePage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import org.openqa.selenium.By;

public abstract class BaseHomePage extends BasePage {
    protected By homepageTabLocator = By.cssSelector("input#Home");
    protected By aboutpageTabLocator = By.cssSelector("input#About");
    protected By homepageTabWindowLocator = By.cssSelector("#WelcomeMessage");
    protected By aboutpageTabWindowLocator = By.cssSelector("#AboutTable");

    protected BaseHomePage(SeleniumTestObject testObject) {
        super(testObject);
    }

    @Override
    public boolean isPageLoaded() {
        return UIWaitFactory.getWaitDriver(this.testObject.getWebDriver()).waitUntilVisibleElement(this.homepageTabLocator);
    }

    /**
     * Switches the tabs using the Training Tab View option provided
     * @param viewOption The name of the tab to switch to
     */
    public abstract void switchTabViews(TrainingTabView viewOption) throws Exception;

    /**
     * Checks if the Training Tab View provided is currently in view
     * @param viewOption The name of the tab to check if it's in view
     * @return If the tab requested is in view
     */
    public abstract boolean isTabInView(TrainingTabView viewOption) throws Exception;
}