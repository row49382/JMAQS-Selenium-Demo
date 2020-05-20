package com.magenic.automatedtests.ui.pageobjectmodels.page_elements;

import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.BootstrapModalWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DatePicker;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogOneWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogTwoWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.Slider;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.enums.AlertCloseOptions;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages.BaseErrorPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages.NoErrorPage;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages.PageElementErrorPage;
import com.magenic.automatedtests.ui.support.FileUtil;
import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class PageElementsPage extends BaseSeleniumPageModel {
    public enum DragAndDropType { HTML4, HTML5 }

    public LazyWebElement getPageElementsHeader() {
        return this.getLazyElement(By.cssSelector("#body > div.container.body-content > h2"));
    }

    public LazyWebElement getErrorLinkElement() {
        return this.getLazyElement(By.cssSelector("li#ErrorPageLink > a"), "ErrorLinkElement");
    }

    public LazyWebElement getAsyncPageLinkElement() {
        return this.getLazyElement(By.cssSelector("li#AsyncPageLink > a"), "AsyncPageLinkElement");
    }

    public LazyWebElement getSwaggerPageLinkElement() {
        return this.getLazyElement(By.cssSelector("li#SwaggerPageLink > a"), "SwaggerPageLinkElement");
    }

    public LazyWebElement getShowDialog1Element() {
        return this.getLazyElement(By.cssSelector("#showDialog1"), "ShowDialog1Element");
    }

    public LazyWebElement getShowDialog2Element() {
        return this.getLazyElement(By.cssSelector("#showDialog2"), "ShowDialog2Element");
    }

    public LazyWebElement getJavascriptAlertElement() {
        return this.getLazyElement(By.cssSelector("#javascriptAlertButton"), "JavascriptAlertButton");
    }

    public LazyWebElement getJavascriptAlertWithConfirmButton() {
        return this.getLazyElement(By.cssSelector("#javascriptConfirmAlertButton"));
    }

    public LazyWebElement getBoostrapModelElement() {
        return this.getLazyElement(By.cssSelector("#ItemsToAutomate > button.btn.btn-primary.btn-lg"), "BootstrapModalButton");
    }

    public LazyWebElement getHtml5StartDragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("#HTML5 > #div1"), "StartingDragAndDropElement_Html5");
    }

    public LazyWebElement getHtml5TargetDragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("#HTML5 > #div2"), "TargetDragAndDropElement_Html5");
    }

    public LazyWebElement getHtml5DragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("img#draggablleImageHTML5"), "DragAndDropBox_Html5");
    }

    public LazyWebElement getHtml4StartDragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("#html4 > #droppable"), "StartingDragAndDropElement_Html4");
    }

    public LazyWebElement getHtml4TargetDragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("#html4 > #droppable2"), "TargetDragAndDropElement_Html4");
    }

    public LazyWebElement getHtml4DragAndDropBoxElement() {
        return this.getLazyElement(By.cssSelector("#html4 > #draggable"), "DragAndDropBox_Html4");
    }

    public LazyWebElement getHtml4StartDragAndDropText() {
        return this.getLazyElement(By.cssSelector("#droppable > p > #DROPPED"));
    }

    public LazyWebElement getHtml4TargetDragAndDropText() {
        return this.getLazyElement(By.cssSelector("#droppable2 > p > #DROPPED"));
    }

    public LazyWebElement getDatePickerButton() {
        return this.getLazyElement(By.cssSelector("img.ui-datepicker-trigger"));
    }

    public LazyWebElement getDatePickerInput() {
        return this.getLazyElement(By.cssSelector("input#datepicker"));
    }

    public LazyWebElement getSliderSpanElement() {
        return this.getLazyElement(By.cssSelector("#slider > span"));
    }

    public PageElementsPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    public BaseErrorPage clickErrorPageLink() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getErrorLinkElement().click();
        BaseErrorPage errorPage;

        if (UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForVisibleElement(By.cssSelector(".navbar-header")) != null) {
            errorPage = new NoErrorPage(this.getTestObject());
        }
        else {
            errorPage = new PageElementErrorPage(this.getTestObject());
        }

        return errorPage;
    }

    public AsyncPage clickAsyncPageLink() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getAsyncPageLinkElement().click();
        return new AsyncPage(this.getTestObject());
    }

    public DialogOneWindow clickDialogOneButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getShowDialog1Element().click();
        return new DialogOneWindow(this.getTestObject());
    }

    public DialogTwoWindow clickDialogTwoButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getShowDialog2Element().click();
        return new DialogTwoWindow(this.getTestObject());
    }

    public BootstrapModalWindow clickBootstrapModalButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getBoostrapModelElement().click();
        return new BootstrapModalWindow(this.getTestObject());
    }

    public DatePicker clickDatePickerButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getDatePickerButton().click();
        return new DatePicker(this.getTestObject());
    }

    public void moveDragAndDropToTarget(DragAndDropType type) throws IOException {
        switch (type) {
            case HTML4: {
                this.moveDragAndDrop(type, this.getHtml4TargetDragAndDropBoxElement());
                break;
            }
            case HTML5: {
                this.moveDragAndDrop(type, this.getHtml5TargetDragAndDropBoxElement());
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void moveDragAndDropToStart(DragAndDropType type) throws IOException {
        switch (type) {
            case HTML4: {
                this.moveDragAndDrop(type, this.getHtml4StartDragAndDropBoxElement());
                break;
            }
            case HTML5: {
                this.moveDragAndDrop(type, this.getHtml5StartDragAndDropBoxElement());
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private void moveDragAndDrop(DragAndDropType type, LazyWebElement target) throws IOException {
        LazyWebElement source;

        switch (type) {
            case HTML4: {
                source = this.getHtml4DragAndDropBoxElement();

                Actions action = new Actions(this.getWebDriver());
                action.dragAndDrop(source.getRawExistingElement(), target.getRawExistingElement())
                        .build()
                        .perform();
                break;
            }
            case HTML5: {
                // HTML5 drag and drop is not supported by selenium,
                // we need to use the javascript file to do so
                source = this.getHtml5DragAndDropBoxElement();

                String javascript = FileUtil.readFile("drag_and_drop_helper.js");

                ((JavascriptExecutor)this.getWebDriver())
                        .executeScript(
                                javascript + "simulateHTML5DragAndDrop(arguments[0], arguments[1])",
                                source.getRawExistingElement(),
                                target.getRawExistingElement());

                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public boolean isHtml5DragAndDropOnElement(LazyWebElement element) {
        boolean isDragAndDropOnElement;

        try {
            isDragAndDropOnElement = element.findElement(this.getHtml5DragAndDropBoxElement().getBy())
                    .doesExist();
        }
        catch (TimeoutException | InterruptedException e) {
            isDragAndDropOnElement = false;
        }

        return isDragAndDropOnElement;
    }

    public boolean isHtml4DragAndDropOnElement(LazyWebElement dropElement, LazyWebElement dropElementText) {
        boolean isDragAndDropOnElement;

        try {
            isDragAndDropOnElement = dropElement.findElement(dropElementText.getBy())
                    .doesExist();
        }
        catch (TimeoutException | InterruptedException e) {
            isDragAndDropOnElement = false;
        }

        return isDragAndDropOnElement;
    }

    public void clickJavaScriptAlertButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getJavascriptAlertElement().click();
    }

    public void clickJavaScriptAlertWithConfirmButton() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getJavascriptAlertWithConfirmButton().click();
    }

    public void closeJavaScriptAlert(AlertCloseOptions closeOption) {
        switch (closeOption) {
            case ACCEPT: {
                this.getTestObject().getWebDriver().switchTo().alert().accept();
                break;
            }
            case DISMISS: {
                this.getTestObject().getWebDriver().switchTo().alert().dismiss();
                break;
            }
        }
    }

    public Slider getSlider() {
        return new Slider(this.getTestObject());
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getPageElementsHeader().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }
}
