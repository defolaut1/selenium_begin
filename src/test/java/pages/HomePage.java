package pages;

import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final String URL = "https://lmslite47vr.demo.mirapolis.ru/mira/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public boolean atPage() {
        return driver.getTitle().equals("Главная страница");
    }
}
