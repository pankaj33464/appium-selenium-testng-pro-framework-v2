package framework.utils;

import framework.core.ConfigManager;
import framework.drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private static WebDriverWait webWait() {
        return new WebDriverWait(DriverFactory.web(), Duration.ofSeconds(ConfigManager.cfg().explicitTimeout()));
    }
    public static WebElement visible(WebElement el) { return webWait().until(ExpectedConditions.visibilityOf(el)); }
    public static WebElement clickable(WebElement el) { return webWait().until(ExpectedConditions.elementToBeClickable(el)); }
}
