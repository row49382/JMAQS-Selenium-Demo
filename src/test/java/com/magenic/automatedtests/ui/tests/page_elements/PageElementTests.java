package com.magenic.automatedtests.ui.tests.page_elements;

import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.AsyncPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.PageElementsPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.BootstrapModalWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogOneWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogTwoWindow;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

//    @Test
//    public void testErrorPageLoadsFromLink() {
//        PageElementsPage pageElementsPage = new PageElementsPage(this.getTestObject());
//        pageElementsPage.clickErrorPageLink();
//    }

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
}
