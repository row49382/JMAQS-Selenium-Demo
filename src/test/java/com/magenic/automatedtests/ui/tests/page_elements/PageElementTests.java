package com.magenic.automatedtests.ui.tests.page_elements;

import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.AsyncPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.PageElementsPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.BootstrapModalWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DatePicker;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogOneWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogTwoWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages.BaseErrorPage;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Month;

public class PageElementTests extends BaseSeleniumTest {
    private final String pageElementsPath = "Automation";

    @BeforeMethod
    public void navigateToStartPage() {
        this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + pageElementsPath);
        UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    }

    @Test
    public void testElementsPageLoaded() {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Assert.assertTrue(pageElementsPage.isPageLoaded());
    }

    @Test(enabled = false)
    public void testErrorPageLoadsFromLink() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        BaseErrorPage errorPage = pageElementsPage.clickErrorPageLink();

        Assert.assertTrue(errorPage.isPageLoaded());
    }

    @Test
    public void testAsyncPageLoadsFromLink() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Assert.assertTrue(asyncPage.isPageLoaded());
    }

    @Test
    public void testAsyncPageDivLoaderDisappears() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Assert.assertTrue(asyncPage.isPageLoaded());
        Assert.assertTrue(asyncPage.didAsyncLoadingDivDisappear());
    }

    @Test
    public void testDialogOneLoads() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        DialogOneWindow d1 = pageElementsPage.clickDialogOneButton();

        Assert.assertTrue(d1.isPageLoaded());
    }

    @Test
    public void testDialogOneCloses() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        DialogOneWindow d1 = pageElementsPage.clickDialogOneButton();

        Assert.assertTrue(d1.isPageLoaded());
        d1.close();

        Assert.assertFalse(d1.isPageLoaded());
    }

    @Test
    public void testDialogTwoLoads() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        DialogTwoWindow d2 = pageElementsPage.clickDialogTwoButton();

        Assert.assertTrue(d2.isPageLoaded());
    }

    @Test
    public void testDialogTwoCloses() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        DialogTwoWindow d2 = pageElementsPage.clickDialogTwoButton();

        Assert.assertTrue(d2.isPageLoaded());
        d2.clickOk();

        Assert.assertFalse(d2.isPageLoaded());
    }

    @Test
    public void testBootstrapModalLoads() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        BootstrapModalWindow bootstrapModalWindow = pageElementsPage.clickBootstrapModalButton();

        Assert.assertTrue(bootstrapModalWindow.isPageLoaded());
    }

    @Test
    public void testBootstrapModalCloseWithButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        BootstrapModalWindow bootstrapModalWindow = pageElementsPage.clickBootstrapModalButton();
        Assert.assertTrue(bootstrapModalWindow.isPageLoaded());

        bootstrapModalWindow.close(BootstrapModalWindow.CloseOption.CLOSE_BUTTON);
        Assert.assertFalse(bootstrapModalWindow.isPageLoaded());
    }

    @Test
    public void testBootstrapModalCloseWithX() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        BootstrapModalWindow bootstrapModalWindow = pageElementsPage.clickBootstrapModalButton();
        Assert.assertTrue(bootstrapModalWindow.isPageLoaded());

        bootstrapModalWindow.close(BootstrapModalWindow.CloseOption.X);

        Assert.assertFalse(bootstrapModalWindow.isPageLoaded());
    }

    // Selenium does not support HTML5 drag and drop natively. We will need to
    // find a solution via javascript
    @Test(enabled = false)
    public void testHtml5DragAndDropStartToTarget() {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToTarget(PageElementsPage.DragAndDropType.HTML5);

        Assert.assertTrue(pageElementsPage.isHtml5DragAndDropOnElement(
                pageElementsPage.getHtml5DragAndDropBoxElement()));
    }

    @Test
    public void testHtml4DragAndDropToTarget() {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToTarget(PageElementsPage.DragAndDropType.HTML4);

        Assert.assertTrue(pageElementsPage.isHtml4DragAndDropOnElement(
                pageElementsPage.getHtml4TargetDragAndDropBoxElement(),
                pageElementsPage.getHtml4TargetDragAndDropText()));
    }

    @Test
    public void testHtml4DragAndDropToStart() {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToStart(PageElementsPage.DragAndDropType.HTML4);

        Assert.assertTrue(pageElementsPage.isHtml4DragAndDropOnElement(
                pageElementsPage.getHtml4StartDragAndDropBoxElement(),
                pageElementsPage.getHtml4StartDragAndDropText()));
    }

    @Test
    public void testDatePickerLoads() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        DatePicker dp = pageElementsPage.clickDatePickerButton();

        Assert.assertTrue(dp.isPageLoaded());
    }

    @Test
    public void testSetDatePickerMonth() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        DatePicker dp = pageElementsPage.clickDatePickerButton();
        dp.isPageLoaded();

        dp.setMonth(Month.FEBRUARY);

        Assert.assertTrue(dp.getCurrentMonth().getText().equalsIgnoreCase(Month.FEBRUARY.toString()));
    }

    @Test
    public void testSetDatePickerYear() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        DatePicker dp = pageElementsPage.clickDatePickerButton();
        dp.isPageLoaded();

        dp.setYear(2018);

        Assert.assertEquals(Integer.parseInt(dp.getCurrentYear().getText()), 2018);
    }

    @Test
    public void testSetDatePickerDay() throws Exception {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        DatePicker dp = pageElementsPage.clickDatePickerButton();
        dp.isPageLoaded();

        dp.setMonth(Month.MARCH);
        dp.setDay(8);

        String day = ((String)((JavascriptExecutor) this.getWebDriver())
                .executeScript("return document.getElementById('datepicker').value"))
                .split("/")[1];

        Assert.assertEquals("08", day);
    }

    @Test
    public void testSetDatePickerFullCompleteDate() throws Exception {
        String expectedDate = "10/02/2016";

        Month month = Month.OCTOBER;
        int day = 2;
        int year = 2016;

        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        DatePicker dp = pageElementsPage.clickDatePickerButton();
        dp.isPageLoaded();

        dp.setYear(year);
        dp.setMonth(month);
        dp.setDay(day);

        String actualDate = ((String)((JavascriptExecutor) this.getWebDriver())
                .executeScript("return document.getElementById('datepicker').value"));

        Assert.assertEquals(expectedDate, actualDate);
    }
}
