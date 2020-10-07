package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final JavascriptExecutor js;
    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "logout-msg")
    private WebElement logoutMsg;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    public String returnLogoutMsg() {
        return logoutMsg.getText();
    }
}
