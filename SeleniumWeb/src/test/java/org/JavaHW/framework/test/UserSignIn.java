package org.JavaHW.framework.test;

import org.JavaHW.framework.data.Repository;
import org.JavaHW.framework.data.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;


public class UserSignIn extends TestRunner {

    @Test
    public void verifyTitle() {
        logger.info("Start verifying title");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("GreenCity"));
        logger.info("Finish verifying title");
        logger.info("Test executed");
    }

    private static Stream<User> userDataValid() {
        return Stream.of(
                Repository.getValidUser());
    }

    @DisplayName("Should display the username of the signed in user - positive scenario")
    @ParameterizedTest
    @MethodSource("userDataValid")
    public void testLoginValid(User user) {
        logger.info("Start signIn with user = " + user);
        String expectedUserName = user.getUsername();
        String actualUserName = testFunctions.signIn(user);
        logger.info("Done signIn with user = " + user);
        Assertions.assertEquals(actualUserName,expectedUserName);
        logger.info("Test executed");
    }

    private static Stream<User> userDataInvalid() {
        return Stream.of(
                Repository.getInvalidUser());
    }

    @DisplayName("Should display the username of the signed in user - negative scenario")
    @ParameterizedTest
    @MethodSource("userDataInvalid")
    public void testLoginInvalid(User user) {
        logger.info("Start signIn with user = " + user);
        String expectedUserName = user.getUsername();
        String actualUserName = testFunctions.signIn(user);
        logger.info("Done signIn with user = " + user);
        Assertions.assertNotEquals(actualUserName, expectedUserName);
        logger.info("Test executed");
    }

    private static Stream<Arguments> invalidEmails() {
        return Stream.of(
                Arguments.of(Repository.getInvalidEmails1()),
                Arguments.of(Repository.getInvalidEmails2()),
                Arguments.of(Repository.getInvalidEmails3())
        );
    }

    @DisplayName("Should display a warning about the invalid email")
    @ParameterizedTest
    @MethodSource("invalidEmails")
    public void signInNotValid(User user) {
        logger.info("Start checking email for = " + user);
        String expectedErrorMessage = "Please check if the email is written correctly";
        String actualErrorMessage = testFunctions.signInvalidEmail(user);
        logger.info("Done checking email for = " + user);
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
        logger.info("Test executed");
    }

    private static Stream<User> EmptyPass() {
        return Stream.of(
                Repository.getEmptyPass());
    }

    @DisplayName("Should display a warning about an empty password")
    @ParameterizedTest
    @MethodSource("EmptyPass")
    public void passwordNotValid(User user) {
        logger.info("Start checking password for = " + user);
        String expectedErrorPassMessage = "Password must be at least 8 characters long without spaces";
        String actualErrorPassMessage = testFunctions.signInEmptyPass(user);
        logger.info("Finish checking password for = " + user);
        Assertions.assertEquals(expectedErrorPassMessage, actualErrorPassMessage);
        logger.info("Test executed");
    }


    private static Stream<Arguments> invalidPasswords() {
        return Stream.of(
                Arguments.of(Repository.getInvalidPass1()),
                Arguments.of(Repository.getInvalidPass2()),
                Arguments.of(Repository.getInvalidPass3())
        );
    }

    @DisplayName("Should display a warning about the incorrect password")
    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void signInvalidPass(User user) {
        logger.info("Start checking password for = " + user);
        String expectedInErrorPassMessage = "Bad email or password";
        String actualInErrorPassMessage = testFunctions.signInvalidPass(user);
        logger.info("Finish checking password for = " + user);
        Assertions.assertEquals(expectedInErrorPassMessage, actualInErrorPassMessage);
        logger.info("Test executed");

    }


    private static Stream<Arguments> passwordReset() {
        return Stream.of(
                Arguments.of(Repository.getPassReset1()),
                Arguments.of(Repository.getPassReset2())
        );
    }

    @DisplayName("Password reset option should be available")
    @ParameterizedTest
    @MethodSource("passwordReset")
    public void passResetOption(User user) {
        logger.info("Start checking password reset option for = " + user);
        String expectedResult = "passResetButton.isEnabled() = true";
        String actualResult = testFunctions.passResetOption(user);
        logger.info("Finish checking password reset option for = " + user);
        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test executed");
    }

    @Test
    public void signUpInstead() {
        logger.info("Test started");
        String expectedResult = "Please enter your details to sign up.";
        String actualResult = testFunctions.signUpInstead();

        Assertions.assertEquals(expectedResult, actualResult);
        logger.info("Test executed");
        }
    }


