package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private final WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(webDriver);
    }

    public  HomePage getHomePage(){
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }
}
