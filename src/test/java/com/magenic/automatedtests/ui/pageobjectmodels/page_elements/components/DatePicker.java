package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.components;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.LazyWebElement;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.openqa.selenium.By;

import java.time.Month;
import java.util.List;

public class DatePicker extends BaseSeleniumPageModel {

    public LazyWebElement getCurrentMonth() {
        return this.getLazyElement(By.cssSelector("span.ui-datepicker-month"));
    }

    public LazyWebElement getCurrentYear() {
        return this.getLazyElement(By.cssSelector("span.ui-datepicker-year"));
    }

    public LazyWebElement getPreviousMonth() {
        return this.getLazyElement(By.cssSelector("a.ui-datepicker-prev"));
    }

    public LazyWebElement getNextMonth() {
        return this.getLazyElement(By.cssSelector("a.ui-datepicker-next"));
    }

    public LazyWebElement getIdentifier() {
        return this.getLazyElement(By.cssSelector("div#ui-datepicker-div"));
    }

    public LazyWebElement getDateTable() {
        return this.getLazyElement(By.cssSelector("#ui-datepicker-div > table > tbody"));
    }

    public DatePicker(SeleniumTestObject testObject) {
        super(testObject);
    }

    public void setYear(int year) throws TimeoutException, InterruptedException, ExecutionFailedException {
        if (year == Integer.parseInt(this.getCurrentYear().getText())) {
            return;
        }
        else if (year > Integer.parseInt(this.getCurrentYear().getText())) {
            this.getNextMonth().click();
            setYear(year);
        }
        else if (year < Integer.parseInt(this.getCurrentYear().getText())) {
            this.getPreviousMonth().click();
            setYear(year);
        }
    }

    public void setMonth(Month month) throws TimeoutException, InterruptedException, ExecutionFailedException {
        if (month.toString().equalsIgnoreCase(this.getCurrentMonth().getText())) {
            return;
        }
        else if (month.getValue() >
                 Enum.valueOf(Month.class, this.getCurrentMonth().getText().toUpperCase()).getValue()) {
            this.getNextMonth().click();
            setMonth(month);
        }
        else if (month.getValue() <
                Enum.valueOf(Month.class, this.getCurrentMonth().getText().toUpperCase()).getValue()) {
            this.getPreviousMonth().click();
            setMonth(month);
        }
    }

    public void setDay(int day) throws Exception {
        List<LazyWebElement> weeks = this.getDateTable().findElements(By.cssSelector("tr"));

        for (LazyWebElement week : weeks) {
            List<LazyWebElement> days = week.findElements(By.cssSelector("td > a"));

            for (LazyWebElement d : days) {
                if (Integer.parseInt(d.getText()) == day) {
                    d.click();
                    return;
                }
            }
        }

        throw new Exception(
                String.format("day %d was not found in the date pickers current month and year", day));
    }

    @Override
    public boolean isPageLoaded() {
        return UIWaitFactory.getWaitDriver(this.getWebDriver())
                .getWaitDriver()
                .until(driver -> driver.findElement(this.getIdentifier().getBy())
                        .getAttribute("style").contains("display: block;"));
    }
}
