package org.JavaHW.framework.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PageFactoryCustom {

    WebDriver driver;
    public PageFactoryCustom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "app-header:nth-child(1) .ubs-header-sing-in-img")
    protected WebElement signInButton;

    @FindBy(xpath = ".//app-sign-in/h1")
    protected WebElement welcomeText;

    @FindBy(css = ".title-text")
    protected WebElement welcomeText2;

    @FindBy(xpath = ".//app-sign-in/h2")
    protected WebElement signInDetailsText;

    @FindBy(css = ".subtitle-text")
    protected WebElement signInDetailsText2;

    @FindBy(xpath = ".//input[@id=\"email\"]/preceding-sibling::label")
    protected WebElement emailLabel;

    @FindBy(id = "email")
    protected WebElement emailInput;

    @FindBy(id = "password")
    protected WebElement passwordInput;

    @FindBy(css = ".ubsStyle")
    protected WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    protected WebElement errorMessage;

    @FindBy(xpath = "//*[@id=\"pass-err-msg\"]")
    protected WebElement errorPassword;

    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    protected WebElement errorEmail;

    @FindBy(xpath = ".//*[@class=\"forgot-password-ubs\"]")
    protected WebElement forgotPassword;

    @FindBy(css = ".ubs-send-btn")
    protected WebElement passResetButton;

    @FindBy(css = ".ubs-link")
    protected WebElement signUpNoAccount;

    @FindBy(css = ".ubs-user-name")
    protected WebElement insideAccountUser;

    @FindBy(css = "li.ubs-user-name")
    protected WebElement accUsername;

    @FindBy(css = "li.drop-down-item[role=\"button\"][aria-label=\"sign-out\"]")
    protected WebElement signOutButton;

}


