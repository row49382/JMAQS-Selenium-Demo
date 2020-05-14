package com.magenic.automatedtests.ui.pageobjectmodels.training.page2;

import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseHomePage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.BaseLoginPage;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page1.TrainingHomePage1;
import com.magenic.automatedtests.ui.pageobjectmodels.training.page1.TrainingLoginPage1;
import com.magenic.jmaqs.selenium.SeleniumTestObject;

public class TrainingLoginPage2 extends BaseLoginPage {
    public TrainingLoginPage2(SeleniumTestObject testObject) {
        super(testObject);
    }

    public TrainingHomePage2 loginSuccess(String username, String pass) {
        this.login(username, pass);
        return new TrainingHomePage2(this.testObject);
    }
}