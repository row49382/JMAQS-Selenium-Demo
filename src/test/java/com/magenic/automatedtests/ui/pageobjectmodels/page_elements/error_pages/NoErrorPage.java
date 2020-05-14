package com.magenic.automatedtests.ui.pageobjectmodels.page_elements.error_pages;

import com.magenic.jmaqs.selenium.SeleniumTestObject;
import org.openqa.selenium.By;

public class NoErrorPage extends BaseErrorPage {
    public NoErrorPage(SeleniumTestObject testObject) {
        super(testObject, By.xpath("//body[text()='The page cannot be displayed because an internal server error has occurred.']"));
    }
}
