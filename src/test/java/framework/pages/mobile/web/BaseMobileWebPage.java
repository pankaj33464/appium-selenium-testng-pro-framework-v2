package framework.pages.mobile.web;

import framework.drivers.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseMobileWebPage {
    protected AppiumDriver driver;
    protected BaseMobileWebPage() {
        this.driver = DriverFactory.mobile();
        PageFactory.initElements(driver, this);
    }
}
