package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    public HomePage(WebDriver driver) {
        super(driver);
        URL = "https://lmslite47vr.demo.mirapolis.ru/mira/";
    }

    public boolean atPage() {
        return driver.getTitle().equals("Главная страница");
    }
}
