package org.bdd.stepDefinitions;

import org.bdd.helpers.WebDriverHelper;
import org.pages.PageProvider;

public class MainStepDefs {

    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;

    public MainStepDefs(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    pageProvider = new PageProvider(webDriverHelper.getWebDriver());
    }
}
