package framework.pages.mobile.nativeapp;

import framework.drivers.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseNativePage {
    protected AppiumDriver driver;
    protected BaseNativePage() {
        this.driver = DriverFactory.mobile();
        PageFactory.initElements(driver, this);
    }
}
