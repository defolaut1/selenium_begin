package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ForgetPasswordPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class TestLogin {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ForgetPasswordPage forgetPasswordPage;

    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        forgetPasswordPage = new ForgetPasswordPage(driver);
    }

    @BeforeEach
    public void start() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void end() {
        driver.quit();
    }

    @Test
    public void enterValidLoginAndValidPassword() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.enterLogin("fominaelena");
        loginPage.enterPassword("1P73BP4Z");
        loginPage.clickLogin();

        driver.findElement(By.xpath("//a[contains(@class, 'news')][1]"));
        Assert.assertTrue(homePage.atPage());
    }

    /**
     * Is it normal behavior to throw alert? And handle it like this?
     * Check specs, ask PM or TTL about it
     */
    @Test
    public void enterValidLoginAndInvalidPassword() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.enterLogin("fominaelena");
        loginPage.enterPassword("incorrect123");
        loginPage.clickLogin();

        try {
            driver.findElement(By.xpath("//a[contains(@class, 'news')][1]"));
            Assert.assertTrue(!homePage.atPage());
        } catch (UnhandledAlertException e) {
            System.out.println("Alert text: " + e.getAlertText());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void enterValidLoginAndEmptyPassword() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.enterLogin("fominaelena");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        try {
            driver.findElement(By.xpath("//a[contains(@class, 'news')][1]"));
            Assert.assertTrue(!homePage.atPage());
        } catch (UnhandledAlertException e) {
            System.out.println("Alert text: " + e.getAlertText());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void enterInvalidLoginAndValidPassword() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.enterLogin("justlogin777");
        loginPage.enterPassword("1P73BP4Z");
        loginPage.clickLogin();

        try {
            driver.findElement(By.xpath("//a[contains(@class, 'news')][1]"));
            Assert.assertTrue(!homePage.atPage());
        } catch (UnhandledAlertException e) {
            System.out.println("Alert text: " + e.getAlertText());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void enterEmptyLoginAndEmptyPassword() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.enterLogin("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        try {
            driver.findElement(By.xpath("//a[contains(@class, 'news')][1]"));
            Assert.assertTrue(!homePage.atPage());
        } catch (UnhandledAlertException e) {
            System.out.println("Alert text: " + e.getAlertText());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void clickForgetPasswordAndEnterValidLogin() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.clickForgetPassword();

        try {
            driver.findElement(By.xpath("//input[contains(@placeholder, 'логин')]"));
            Assert.assertTrue(forgetPasswordPage.atPage());

            forgetPasswordPage.enterLoginOrEmail("fominaelena");
            forgetPasswordPage.clickSend();
            Assert.assertTrue(
                    driver.findElement(By.xpath("//div[contains(text(), 'инструкция')]")).isDisplayed()
            );
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void clickForgetPasswordAndEnterInvalidLogin() {
        loginPage.open();
        Assert.assertTrue(loginPage.atPage());
        loginPage.clickForgetPassword();

        try {
            driver.findElement(By.xpath("//input[contains(@placeholder, 'логин')]"));
            Assert.assertTrue(forgetPasswordPage.atPage());

            forgetPasswordPage.enterLoginOrEmail("justlogin777");
            forgetPasswordPage.clickSend();
            Assert.assertTrue(
                    driver.findElement(By.xpath("//div[contains(text(), 'не найден')]")).isDisplayed()
            );
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
            Assert.assertTrue(false);
        }
    }
}
