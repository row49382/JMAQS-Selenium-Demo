package com.magenic.automatedtests.ui.pageobjectmodels.page_elements;

import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.BootstrapModalWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogOneWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components.DialogTwoWindow;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page3.ErrorPage;
import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    public LazyWebElement getAlertWithConfirmElement() {
        return this.getLazyElement(By.cssSelector("#javascriptConfirmAlertButton"), "JavascriptConfirmAlertButton");
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

    public PageElementsPage(SeleniumTestObject testObject) {
        super(testObject);
    }

    public ErrorPage clickErrorPageLink() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.getErrorLinkElement().click();
        return new ErrorPage(this.getTestObject());
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

    public void moveDragAndDropToTarget(DragAndDropType type) {
        WebElement source;
        WebElement target;

        switch (type) {
            case HTML4: {
                source = this.getHtml4DragAndDropBoxElement().getRawExistingElement();
                target = this.getHtml4TargetDragAndDropBoxElement().getRawExistingElement();
                break;
            }
            case HTML5: {
                source = this.getHtml5DragAndDropBoxElement().getRawExistingElement();
                target = this.getHtml5TargetDragAndDropBoxElement().getRawExistingElement();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        Actions action = new Actions(this.getWebDriver());
        action.dragAndDrop(
                source, target)
                .build()
                .perform();

    }

    public void moveDragAndDropToStart(DragAndDropType type) {
        WebElement target;
        WebElement source;

        switch (type) {
            case HTML4: {
                source = this.getHtml4DragAndDropBoxElement().getRawExistingElement();
                target = this.getHtml4StartDragAndDropBoxElement().getRawExistingElement();
                break;
            }
            case HTML5: {
                source = this.getHtml5DragAndDropBoxElement().getRawExistingElement();
                target = this.getHtml5StartDragAndDropBoxElement().getRawExistingElement();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        Actions action = new Actions(this.getWebDriver());
        action.dragAndDrop(source, target)
                .build()
                .perform();
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

    @Override
    public boolean isPageLoaded() {
        try {
            return this.getPageElementsHeader().isDisplayed();
        } catch (TimeoutException | InterruptedException e) {
            return false;
        }
    }
}
