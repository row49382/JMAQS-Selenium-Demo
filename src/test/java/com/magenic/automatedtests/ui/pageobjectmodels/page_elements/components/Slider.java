package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components;

import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class Slider {
    private final LazyWebElement sliderNumber;
    private final LazyWebElement sliderMover;

    public Slider(SeleniumTestObject testObject) {
        this.sliderMover = new LazyWebElement(testObject, By.cssSelector("#slider > span"), "SliderMover");
        this.sliderNumber = new LazyWebElement(testObject, By.cssSelector("input#sliderNumber"), "SliderNumber");
    }

    public void shiftRight() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.sliderMover.click();
        this.sliderMover.sendKeys(Keys.ARROW_RIGHT);
    }

    public void shiftLeft() throws InterruptedException, TimeoutException, ExecutionFailedException {
        this.sliderMover.click();
        this.sliderMover.sendKeys(Keys.ARROW_LEFT);
    }

    public void shiftMax() throws TimeoutException, InterruptedException, ExecutionFailedException {
        while (!this.getPosition().equalsIgnoreCase("left: 100%;")) {
            this.shiftRight();
        }
    }

    public void shiftMin() throws InterruptedException, TimeoutException, ExecutionFailedException {
        while(!this.getPosition().equals("left: 0%;")) {
            this.shiftLeft();
        }
    }

    public int getCurrentValue() {
        return Integer.parseInt(((JavascriptExecutor)this.sliderNumber.getTestObject().getWebDriver())
                .executeScript("return arguments[0].value", this.sliderNumber.getRawExistingElement())
                .toString());
    }

    private String getPosition() throws TimeoutException, InterruptedException {
        return this.sliderMover.getAttribute("Style");
    }
}
