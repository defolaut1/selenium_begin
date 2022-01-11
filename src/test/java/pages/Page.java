package pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    protected WebDriver driver;
    protected String URL;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }
}
