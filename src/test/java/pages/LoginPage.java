package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private final String URL = "https://lmslite47vr.demo.mirapolis.ru/mira";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public boolean atPage() {
        return driver.getTitle().equals("Авторизация");
    }

    public void enterLogin(String login) {
        driver.findElement(By.xpath("//input[contains(@placeholder, 'логин')]")).sendKeys(login);
    }

    public void enterPassword(String password) {
        driver.findElement(By.xpath("//input[contains(@placeholder, 'пароль')]")).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void clickForgetPassword() {
        driver.findElement(By.xpath("//a[contains(@class, 'default')]")).click();
    }
}
