package framework.drivers;

import framework.core.ConfigManager;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.util.Arrays;

public class MobileOptionsFactory {

    public static UiAutomator2Options androidNative() {
        UiAutomator2Options o = new UiAutomator2Options()
                .setUdid(ConfigManager.cfg().androidUdid())
                .setDeviceName(ConfigManager.cfg().androidDeviceName())
                .setPlatformVersion(ConfigManager.cfg().androidPlatformVersion())
                .setAutoGrantPermissions(ConfigManager.cfg().androidAutoGrantPermissions());
        // App under test
        return o
                .setAppPackage(System.getProperty("android.appPackage", ""))
                .setAppActivity(System.getProperty("android.appActivity", ""));
    }

    public static UiAutomator2Options androidWeb() {
        UiAutomator2Options o = new UiAutomator2Options()
                .setUdid(ConfigManager.cfg().androidUdid())
                .setDeviceName(ConfigManager.cfg().androidDeviceName())
                .setPlatformVersion(ConfigManager.cfg().androidPlatformVersion())
                .withBrowserName("Chrome");
        o.setChromedriverArgs(Arrays.asList(
                ConfigManager.cfg().androidChromeArgs().split("\\s+")
        ));
        return o;
    }

    public static XCUITestOptions iosNative() {
        return new XCUITestOptions()
                .setUdid(ConfigManager.cfg().iosUdid())
                .setDeviceName(ConfigManager.cfg().iosDeviceName())
                .setPlatformVersion(ConfigManager.cfg().iosPlatformVersion())
                .setBundleId(ConfigManager.cfg().iosBundleId())
                .setAutoAcceptAlerts(ConfigManager.cfg().iosAutoAcceptAlerts());
    }

    public static XCUITestOptions iosWeb() {
        return new XCUITestOptions()
                .setUdid(ConfigManager.cfg().iosUdid())
                .setDeviceName(ConfigManager.cfg().iosDeviceName())
                .setPlatformVersion(ConfigManager.cfg().iosPlatformVersion())
                .withBrowserName("Safari")
                .setAutoAcceptAlerts(ConfigManager.cfg().iosAutoAcceptAlerts());
    }
}
