package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

    private final JavascriptExecutor js;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addNewCredential")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "credentialSubmitModal")
    private WebElement submitCredentialModalButton;

    @FindBy(id = "successMsg")
    private WebElement successMsg;

    @FindBy(className = "credentialurl")
    private WebElement credentialUrl;

    @FindBy(className = "credentialusername")
    private WebElement credentialUsername;

    @FindBy(className = "credentialpassword")
    private WebElement credentialPassword;

    @FindBy(css = "#credentialTable .btn-success")
    private WebElement editButton;

    @FindBy(css = "#credentialTable .btn-danger")
    private WebElement deleteButton;

    @FindBy(id = "returnHome")
    private WebElement returnHome;

    public CredentialsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void insertNewCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        js.executeScript("arguments[0].click();", credentialsTab);
        js.executeScript("arguments[0].click();", addCredentialButton);
        js.executeScript("arguments[0].value='" + credentialUrl + "';", credentialUrlField);
        js.executeScript("arguments[0].value='" + credentialUsername + "';", credentialUsernameField);
        js.executeScript("arguments[0].value='" + credentialPassword + "';", credentialPasswordField);
        js.executeScript("arguments[0].click();", submitCredentialModalButton);
    }

    public String returnUncryptedPassword() {
        js.executeScript("arguments[0].click();", credentialsTab);
        js.executeScript("arguments[0].click();", editButton);
        return editButton.getAttribute("data-password");
    }

    public void editCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        js.executeScript("arguments[0].click();", credentialsTab);
        js.executeScript("arguments[0].click();", editButton);
        js.executeScript("arguments[0].value='" + credentialUrl + "';", credentialUrlField);
        js.executeScript("arguments[0].value='" + credentialUsername + "';", credentialUsernameField);
        js.executeScript("arguments[0].value='" + credentialPassword + "';", credentialPasswordField);
        js.executeScript("arguments[0].click();", submitCredentialModalButton);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", credentialsTab);
        js.executeScript("arguments[0].click();", deleteButton);
    }

    public String returnSuccessMsg() {
        return successMsg.getText();
    }

    public String getCredentialsUrl() {
        js.executeScript("arguments[0].click();", credentialsTab);
        return credentialUrl.getText();
    }

    public String getCredentialsUsername() {
        js.executeScript("arguments[0].click();", credentialsTab);
        return credentialUsername.getText();
    }

    public String getCredentialsPassword() {
        js.executeScript("arguments[0].click();", credentialsTab);
        return credentialPassword.getText();
    }

    public void returnHome() {
        js.executeScript("arguments[0].click();", returnHome);
    }
}
