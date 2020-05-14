package com.magenic.automatedtests.ui.tests.training;

import com.magenic.automatedtests.ui.pageobjectmodels.training.page1.TrainingHomePage1;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page1.TrainingLoginPage1;
import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainingPage1Tests extends BaseSeleniumTest {
    private final String trainingPagePath = "Static/Training1/loginpage.html";

    @BeforeMethod
    public void navigateToStartPage() {
        this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + trainingPagePath);
        UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    }

    @Test
    public void testValidCredentialsLoadsHomePage() {
        String username = "Ted";
        String password = "123";

        TrainingLoginPage1 loginPage = new TrainingLoginPage1(this.getTestObject());
        TrainingHomePage1 homePage = loginPage.loginSuccess(username, password);

        Assert.assertTrue(homePage.isPageLoaded());
    }

    @Test
    public void testInvalidCredentialsDisplaysError() throws TimeoutException, InterruptedException {
        String invalidUsername = "det";
        String password = "123";

        TrainingLoginPage1 loginPage = new TrainingLoginPage1(this.getTestObject());
        String errorMessage = loginPage.loginInvalid(invalidUsername, password);

        Assert.assertNotNull(errorMessage);
        Assert.assertEquals(errorMessage, "Invalid name or password");
    }

    @Test
    public void testHomePageTabStartsOnHome() throws Exception {
        TrainingHomePage1 homePage = this.login();
        Assert.assertTrue(homePage.isTabInView(TrainingTabView.HOME));
    }

    @Test
    public void testSwitchViewsToAboutTab() throws Exception {
        TrainingHomePage1 homePage = this.login();
        homePage.switchTabViews(TrainingTabView.ABOUT);

        Assert.assertTrue(homePage.isTabInView(TrainingTabView.ABOUT));
    }

    /**
     * logins using valid credentials and loads the homepage
     * @return The loaded homepage
     * @throws Exception If the homepage fails to load
     */
    private TrainingHomePage1 login() throws Exception {
        String username = "Ted";
        String password = "123";

        TrainingLoginPage1 loginPage = new TrainingLoginPage1(this.getTestObject());
        TrainingHomePage1 homePage = loginPage.loginSuccess(username, password);

        if (!homePage.isPageLoaded()) {
            throw new Exception("home page failed to load");
        };

        return homePage;
    }
}
