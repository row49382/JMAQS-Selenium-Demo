package com.magenic.automatedtests.ui.tests.page_elements;

import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.AsyncPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.PageElementsPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.BootstrapModalWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DatePicker;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogOneWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogTwoWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.Slider;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.enums.AlertCloseOptions;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages.BaseErrorPage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.enums.TrainingTabView;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page3.TrainingHomePage3;
import com.magenic.automatedtests.ui.support.FileUtil;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.time.Month;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    @Test
    public void testHtml5DragAndDropStartToTarget() throws IOException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToTarget(PageElementsPage.DragAndDropType.HTML5);

        Assert.assertTrue(pageElementsPage.isHtml5DragAndDropOnElement(
                pageElementsPage.getHtml5TargetDragAndDropBoxElement()));
    }

    @Test
    public void testHtml5DragAndDropStartToTargetBackToStart() throws IOException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToTarget(PageElementsPage.DragAndDropType.HTML5);
        pageElementsPage.moveDragAndDropToStart(PageElementsPage.DragAndDropType.HTML5);

        Assert.assertTrue(pageElementsPage.isHtml5DragAndDropOnElement(
                pageElementsPage.getHtml5StartDragAndDropBoxElement()));
    }

    @Test
    public void testHtml4DragAndDropToTarget() throws IOException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.isPageLoaded();

        pageElementsPage.moveDragAndDropToTarget(PageElementsPage.DragAndDropType.HTML4);

        Assert.assertTrue(pageElementsPage.isHtml4DragAndDropOnElement(
                pageElementsPage.getHtml4TargetDragAndDropBoxElement(),
                pageElementsPage.getHtml4TargetDragAndDropText()));
    }

    @Test
    public void testHtml4DragAndDropToStart() throws IOException {
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
                .executeScript(
                        "return arguments[0].value",
                        pageElementsPage.getDatePickerInput().getRawExistingElement()));

        Assert.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testAsyncPageSelectOptions() throws Exception {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Stream<String> expectedSelectOptions = Stream.of("First", "Second", "Third");
        Select select = asyncPage.getSelect();
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
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Select select = asyncPage.getSelect();

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "First");
    }

    @Test
    public void testAsyncPageSetSelectOptionIsSetAsSelected() throws Exception {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Select select = asyncPage.getSelect();
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
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        AsyncPage asyncPage = pageElementsPage.clickAsyncPageLink();

        Select select = asyncPage.getSelect();
        select.selectByValue("Invalid_Option");
    }

    @Test
    public void testCloseJavascriptAlertButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        pageElementsPage.clickJavaScriptAlertButton();

        pageElementsPage.closeJavaScriptAlert(AlertCloseOptions.ACCEPT);
        Alert alert = ExpectedConditions.alertIsPresent().apply(this.getWebDriver());

        Assert.assertNull(alert);
    }

    @Test
    public void testCloseJavaScriptAlertWithConfirmButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Set<AlertCloseOptions> closeOptions = EnumSet.allOf(AlertCloseOptions.class);

        SoftAssert sa = new SoftAssert();
        for (AlertCloseOptions closeOption : closeOptions) {
            pageElementsPage.clickJavaScriptAlertWithConfirmButton();
            pageElementsPage.closeJavaScriptAlert(closeOption);
            Alert alert = ExpectedConditions.alertIsPresent().apply(this.getWebDriver());

            sa.assertNull(alert);
        }

        sa.assertAll();
    }

    @Test
    public void testSliderValueIncrementsWhenShiftedRight() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Slider slider = pageElementsPage.getSlider();

        // make sure value can be incremented
        if (slider.getCurrentValue() == 0) {
            slider.shiftLeft();
        }

        int currentValue = slider.getCurrentValue();

        slider.shiftRight();
        int incrementedValue = slider.getCurrentValue();

        Assert.assertEquals(incrementedValue - currentValue, 1);
    }

    @Test
    public void testSliderValueDecrementsWhenShiftedLeft() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Slider slider = pageElementsPage.getSlider();

        // make sure value can be decremented
        if (slider.getCurrentValue() == 0) {
            slider.shiftRight();
        }

        int currentValue = slider.getCurrentValue();

        slider.shiftLeft();
        int decrementedValue = slider.getCurrentValue();

        Assert.assertEquals(currentValue - decrementedValue, 1);
    }

    @Test
    public void validateSliderMaxValue() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Slider slider = pageElementsPage.getSlider();
        slider.shiftMax();

        int expectedMax = 10;
        int actualMax = slider.getCurrentValue();

        Assert.assertEquals(expectedMax, actualMax);
    }

    @Test
    public void validateSliderMinValue() throws InterruptedException, TimeoutException, ExecutionFailedException {
        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
        Slider slider = pageElementsPage.getSlider();
        slider.shiftMin();

        int expectedMin = 1;
        int actualMin = slider.getCurrentValue();

        Assert.assertEquals(expectedMin, actualMin);
    }

}
