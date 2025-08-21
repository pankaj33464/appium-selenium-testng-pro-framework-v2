package framework.drivers;

import framework.core.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> WEB = new ThreadLocal<>();
    private static final ThreadLocal<AppiumDriver> MOBILE = new ThreadLocal<>();

    public static void initDriver() {
        String platform = ConfigManager.cfg().platform().toLowerCase();
        switch (platform) {
            case "web" -> initWeb();
            case "android_native" -> initAndroidNative();
            case "android_web" -> initAndroidWeb();
            case "ios_native" -> initIOSNative();
            case "ios_web" -> initIOSWeb();
            default -> throw new IllegalArgumentException("Unknown platform: " + platform);
        }
    }

    private static void initWeb() {
        String browser = ConfigManager.cfg().webBrowser().toLowerCase();
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeDriver driver = new ChromeDriver((org.openqa.selenium.chrome.ChromeOptions) WebOptionsFactory.create());
                setCommonWebDriverSettings(driver);
                WEB.set(driver);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxDriver driver = new FirefoxDriver((org.openqa.selenium.firefox.FirefoxOptions) WebOptionsFactory.create());
                setCommonWebDriverSettings(driver);
                WEB.set(driver);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeDriver driver = new EdgeDriver((org.openqa.selenium.edge.EdgeOptions) WebOptionsFactory.create());
                setCommonWebDriverSettings(driver);
                WEB.set(driver);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static void setCommonWebDriverSettings(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.cfg().implicitTimeout()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.cfg().pageLoadTimeout()));
        driver.manage().window().maximize();
    }

    private static void initAndroidNative() {
        try {
            AndroidDriver driver = new AndroidDriver(URI.create(ConfigManager.cfg().appiumServer()).toURL(), MobileOptionsFactory.androidNative());
            MOBILE.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initAndroidWeb() {
        try {
            AndroidDriver driver = new AndroidDriver(URI.create(ConfigManager.cfg().appiumServer()).toURL(), MobileOptionsFactory.androidWeb());
            MOBILE.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initIOSNative() {
        try {
            IOSDriver driver = new IOSDriver(URI.create(ConfigManager.cfg().appiumServer()).toURL(), MobileOptionsFactory.iosNative());
            MOBILE.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initIOSWeb() {
        try {
            IOSDriver driver = new IOSDriver(URI.create(ConfigManager.cfg().appiumServer()).toURL(), MobileOptionsFactory.iosWeb());
            MOBILE.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver web() { return WEB.get(); }
    public static AppiumDriver mobile() { return MOBILE.get(); }

    public static void quitDriver() {
        WebDriver web = WEB.get();
        AppiumDriver mob = MOBILE.get();
        if (web != null) {
            attachScreenshotIfPossible(web);
            web.quit();
            WEB.remove();
        }
        if (mob != null) {
            attachScreenshotIfPossible(mob);
            mob.quit();
            MOBILE.remove();
        }
    }

    @Attachment(value = "Last screenshot", type = "image/png")
    private static byte[] attachScreenshotIfPossible(Object driver) {
        try {
            if (driver instanceof TakesScreenshot ts) {
                return ts.getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception ignored) {}
        return new byte[0];
    }
}
