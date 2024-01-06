package org.JavaHW.framework.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.JavaHW.framework.library.PageFactoryCustom;
import org.JavaHW.framework.library.TestFunctions;
import org.JavaHW.framework.tools.LocalStorageJS;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ExtendWith(RunnerExtension.class)

public abstract class TestRunner {

    private static final String BASE_URL = "https://www.greencity.social/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";


    protected static TestFunctions testFunctions;
    protected static PageFactoryCustom pageFactoryCustom;
    protected static WebDriver driver;
    protected static LocalStorageJS localStorageJS;
    protected static boolean isTestSuccessful = false;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    protected void presentationSleep() {
        presentationSleep(5);
    }

    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenShot() {
        logger.debug("Start takeScreenShot()");
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void takePageSource() {
        logger.debug("Start takePageSource()");
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        localStorageJS = new LocalStorageJS(driver);
        System.out.println("@BeforeAll executed");

    }
    @BeforeEach
    public void setupThis() {
        driver.get(BASE_URL);
        pageFactoryCustom = new org.JavaHW.framework.library.PageFactoryCustom(driver);
        testFunctions = new TestFunctions(driver, pageFactoryCustom);

        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    //public void tearThis()
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            logger.error("Test_Name = " + testInfo.getDisplayName() + " failed");

            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
            takeScreenShot();
            takePageSource();
        }
        driver.manage().deleteAllCookies();
        localStorageJS.clearLocalStorage();
        localStorageJS.removeItemFromLocalStorage("accessToken");
        localStorageJS.removeItemFromLocalStorage("refreshToken");
        presentationSleep(4);
        driver.navigate().refresh();
        System.out.println("\t@AfterEach executed");}

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("@AfterAll executed");
    }

}
