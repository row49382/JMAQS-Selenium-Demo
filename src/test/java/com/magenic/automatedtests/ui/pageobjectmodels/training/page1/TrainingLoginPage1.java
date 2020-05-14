package com.magenic.automatedtests.ui.pageobjectmodels.training.page1;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseLoginPage;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;

public class TrainingLoginPage1 extends BaseLoginPage {

    public TrainingLoginPage1(SeleniumTestObject testObject) {
        super(testObject);
    }

    public TrainingHomePage1 loginSuccess(String username, String pass) {
        this.login(username, pass);
        return new TrainingHomePage1(this.testObject);
    }
}
