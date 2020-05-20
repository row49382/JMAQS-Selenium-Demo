package com.magenic.automatedtests.ui.tests.training;

import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page2.TrainingHomePage2;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page2.TrainingLoginPage2;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TrainingPage2Tests extends BaseSeleniumTest {
    private final String trainingPagePath = "Static/Training2/loginpage.html";

    @DataProvider(name = "TabOptions")
    public static Object[][] getTabOptions() {
        return new Object[][] {
                { "HOME" },
                { "ABOUT" },
                { "ASYNC_PAGE" },
                { "HOW_IT_WORKS" }
        };
    }

    @BeforeMethod
    public void navigateToStartPage() {
        this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + trainingPagePath);
    }

    @Test
    public void testValidCredentialsLoadsHomePage() {
        String username = "Ted";
        String password = "123";

        TrainingLoginPage2 loginPage = new TrainingLoginPage2(this.getTestObject());
        TrainingHomePage2 homePage = loginPage.loginSuccess(username, password);

        Assert.assertTrue(homePage.isPageLoaded());
    }

    @Test
    public void testInvalidCredentialsDisplaysError() throws TimeoutException, InterruptedException {
        String invalidUsername = "det";
        String password = "123";

        TrainingLoginPage2 loginPage = new TrainingLoginPage2(this.getTestObject());
        String errorMessage = loginPage.loginInvalid(invalidUsername, password);

        Assert.assertNotNull(errorMessage);
        Assert.assertEquals(errorMessage, "Invalid name or password");
    }

    @Test
    public void testHomePageTabStartsOnHome() throws Exception {
        TrainingHomePage2 homePage = this.login();
        Assert.assertTrue(homePage.isTabInView(TrainingTabView.HOME));
    }

    @Test(dataProvider = "TabOptions")
    public void testSwitchViewsToAnyTab(String option) throws Exception {
        TrainingTabView tabOption = Enum.valueOf(TrainingTabView.class, option);

        TrainingHomePage2 homePage = this.login();
        homePage.switchTabViews(tabOption);

        Assert.assertTrue(homePage.isTabInView(tabOption));
    }

    @Test
    public void testAsyncPageSelectOptions() throws Exception {
        TrainingHomePage2 homePage = this.login();
        homePage.switchTabViews(TrainingTabView.ASYNC_PAGE);
        homePage.isTabInView(TrainingTabView.ASYNC_PAGE);

        Stream<String> expectedSelectOptions = Stream.of("First", "Second", "Third");
        Select select = homePage.getAsyncPageSelectOption();
        Supplier<Stream<String>> actualSelectOptions =
                () -> select.getOptions().stream().map(WebElement::getText);
        SoftAssert sa = new SoftAssert();

        expectedSelectOptions.forEach(
                expectedOption -> sa.assertTrue(actualSelectOptions
                        .get()
                        .anyMatch(actualOption -> expectedOption.equalsIgnoreCase(actualOption))));

        sa.assertAll();
    }

    @Test
    public void testAsyncPageDefaultSelectOptionIsFirst() throws Exception {
        TrainingHomePage2 homePage = this.login();
        homePage.switchTabViews(TrainingTabView.ASYNC_PAGE);
        homePage.isTabInView(TrainingTabView.ASYNC_PAGE);

        Select select = homePage.getAsyncPageSelectOption();

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "First");
    }
    @Test
    public void testAsyncPageSetSelectOptionIsSetAsSelected() throws Exception {
        TrainingHomePage2 homePage = this.login();
        homePage.switchTabViews(TrainingTabView.ASYNC_PAGE);
        homePage.isTabInView(TrainingTabView.ASYNC_PAGE);

        Select select = homePage.getAsyncPageSelectOption();
        Stream<String> expectedSelectOptions = Stream.of("First", "Second", "Third");

        SoftAssert sa = new SoftAssert();
        expectedSelectOptions.forEach(expectedOption -> {
            select.selectByVisibleText(expectedOption);
            sa.assertEquals(select.getFirstSelectedOption().getText(), expectedOption);
        });

        sa.assertAll();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testAsyncPageSelectOptionThatIsInvalid() throws Exception {
        TrainingHomePage2 homePage = this.login();
        homePage.switchTabViews(TrainingTabView.ASYNC_PAGE);
        homePage.isTabInView(TrainingTabView.ASYNC_PAGE);

        Select select = homePage.getAsyncPageSelectOption();
        select.selectByValue("Invalid_Option");
    }

    /**
     * logins using valid credentials and loads the homepage
     * @return The loaded homepage
     * @throws Exception If the homepage fails to load
     */
    protected TrainingHomePage2 login() throws Exception {
        String username = "Ted";
        String password = "123";

        TrainingLoginPage2 loginPage = new TrainingLoginPage2(this.getTestObject());
        TrainingHomePage2 homePage = loginPage.loginSuccess(username, password);

        if (!homePage.isPageLoaded()) {
            throw new Exception("home page failed to load");
        };

        return homePage;
    }
}
