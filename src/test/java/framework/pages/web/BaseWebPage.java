package framework.pages.web;

import framework.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseWebPage {
    protected WebDriver driver;
    protected BaseWebPage() {
        this.driver = DriverFactory.web();
        PageFactory.initElements(driver, this);
    }
    public String title() { return driver.getTitle(); }
}
