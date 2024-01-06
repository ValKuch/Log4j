package org.JavaHW.framework.library;

import org.JavaHW.framework.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;


public class TestFunctions {
    public static final Logger logger = LoggerFactory.getLogger(TestFunctions.class);

    private WebDriver driver;
    private PageFactoryCustom pageFactoryCustom;


    public TestFunctions(WebDriver driver, PageFactoryCustom factory) {
        this.driver = driver;
        this.pageFactoryCustom = factory;
    }

    public String signIn(User user) {
        try {
            pageFactoryCustom.signInButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            logger.debug("SignIn Button was located");
            wait.until(ExpectedConditions.visibilityOf(pageFactoryCustom.welcomeText));
            pageFactoryCustom.emailInput.sendKeys(user.getEmail());
            pageFactoryCustom.passwordInput.sendKeys(user.getPassword());
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait1.until(ExpectedConditions.elementToBeClickable(pageFactoryCustom.signInSubmitButton));
            pageFactoryCustom.signInSubmitButton.click();
            return pageFactoryCustom.accUsername.getText();

        } catch (Exception e) {
            logger.debug("Something went wrong: login not completed");
            e.printStackTrace();
            return null;
        }
    }

    public String signInvalidEmail(User user) {
        try {
            pageFactoryCustom.signInButton.click();
            logger.debug("SignIn Button was located");
            pageFactoryCustom.emailInput.sendKeys(user.getEmail());
            pageFactoryCustom.passwordInput.sendKeys(user.getPassword());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pageFactoryCustom.errorEmail));
            pageFactoryCustom.signInSubmitButton.click();
            return pageFactoryCustom.errorEmail.getText();
        } catch (Exception e) {
            logger.debug("Something went wrong: email not accepted");
            e.printStackTrace();
            return null;
        }
    }

    public String signInEmptyPass(User user) {
        try {
            pageFactoryCustom.signInButton.click();
            logger.debug("SignIn Button was located");
            pageFactoryCustom.emailInput.sendKeys(user.getEmail());
            pageFactoryCustom.passwordInput.sendKeys(user.getPassword());
            pageFactoryCustom.signInSubmitButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pageFactoryCustom.errorPassword));
            return pageFactoryCustom.errorPassword.getText();
        } catch (Exception e) {
            logger.debug("Something went wrong: password not accepted");
            e.printStackTrace();
            return null;
        }
    }

    public String signInvalidPass(User user) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", pageFactoryCustom.signInButton);
            logger.debug("SignIn Button was located");
            pageFactoryCustom.emailInput.sendKeys(user.getEmail());
            pageFactoryCustom.passwordInput.sendKeys(user.getPassword());
            pageFactoryCustom.signInSubmitButton.click();
            return pageFactoryCustom.errorMessage.getText();
        } catch (Exception e) {
            logger.debug("Something went wrong: password not accepted");
            e.printStackTrace();
            return null;
        }
    }

    public String passResetOption(User user) {
        try {
            pageFactoryCustom.signInButton.click();
            logger.debug("SignIn Button was located");
            pageFactoryCustom.forgotPassword.click();
            pageFactoryCustom.emailInput.sendKeys(user.getEmail());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement passResetButton = (WebElement) (js
                    .executeScript("return document.querySelector('.ubs-send-btn')"));
            return "passResetButton.isEnabled() = " + passResetButton.isEnabled();
        } catch (Exception e) {
            logger.debug("Something went wrong: password reset not available");
            e.printStackTrace();
            return "An error occurred";
        }
    }

    public String signUpInstead() {
        try {
            pageFactoryCustom.signInButton.click();
            logger.debug("SignIn Button was located");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pageFactoryCustom.signUpNoAccount));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement signUpButton = driver.findElement(By.cssSelector(".ubs-link"));
            js.executeScript("arguments[0].style.backgroundColor = 'yellow';", signUpButton);
            pageFactoryCustom.signUpNoAccount.click();
            return pageFactoryCustom.signInDetailsText2.getText();
        } catch (Exception e) {
            logger.debug("Something went wrong: SignUp option not available");
            e.printStackTrace();
            return "An error occurred";
        }
    }
}
