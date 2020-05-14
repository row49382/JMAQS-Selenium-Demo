package com.magenic.automatedtests.ui.pageobjectmodels.training.page3;

import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page2.TrainingHomePage2;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TrainingHomePage3 extends TrainingHomePage2 {
    private By homepageTabLocator = By.cssSelector("input#HomePage");
    private By errorPageLocator = By.cssSelector("#ErrorPagePage");
    private By errorTabContentLocator = By.cssSelector("#NoError");
    private By aboutpageTabLocator = By.cssSelector("input#AboutPage");
    private By asyncpageTabLocator = By.cssSelector("input#AsyncPage");
    private By howDoesItWorkPageTabLocator = By.cssSelector("input#HowWorkPage");
    private By howDoesItWorkPageTabWindowLocator = By.cssSelector("p#HowWorks");

    protected TrainingHomePage3(SeleniumTestObject testObject) {
        super(testObject);
    }

    public ErrorPage swapToErrorPage() throws InterruptedException, TimeoutException {
        ErrorPage errorPage = new ErrorPage(this.testObject);

        GenericWait.waitFor(() -> {
                    this.testObject.getWebDriver().findElement(this.errorPageLocator).click();
                    Assert.assertTrue(errorPage.isPageLoaded());

                    return true;
                });

        return errorPage;
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
            case ERROR: {
                this.testObject.getWebDriver().findElement(this.errorPageLocator).click();
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
            case ERROR: {
                isInView = UIWaitFactory.getWaitDriver(this.testObject.getWebDriver())
                        .waitUntilVisibleElement(this.errorTabContentLocator);
                break;
            }
            default :
                throw new Exception(
                        String.format("view option %s provided is not supported for this page", viewOption.toString()));
        }

        return isInView;
    }

    @Override
    public boolean isPageLoaded() {
        return UIWaitFactory.getWaitDriver(this.testObject.getWebDriver()).waitUntilVisibleElement(this.homepageTabLocator);
    }
}
