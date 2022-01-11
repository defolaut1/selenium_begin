package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage {
    private WebDriver driver;
    private final String URL =
            "https://lmslite47vr.demo.mirapolis.ru/mira//Do?doaction=Go&s=ysSnRhdjQWRFFydAaU3L&id=0&type=remindpassword";

    public ForgetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public boolean atPage() {
        return driver.getTitle().equals("Восстановление пароля");
    }

    public void enterLoginOrEmail(String login) {
        driver.findElement(By.xpath("//input[contains(@placeholder, 'логин')]")).sendKeys(login);
    }

    public void clickSend() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
