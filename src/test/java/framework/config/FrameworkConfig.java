package framework.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface FrameworkConfig extends Config {

    @Key("platform") @DefaultValue("web") String platform();

    // WireMock
    @Key("wiremock.enabled") @DefaultValue("false") boolean wiremockEnabled();
    @Key("wiremock.port") @DefaultValue("9090") int wiremockPort();

    // Web
    @Key("web.browser") @DefaultValue("chrome") String webBrowser();
    @Key("web.baseUrl") @DefaultValue("https://www.google.com") String webBaseUrl();

    // Grid/Cloud
    @Key("grid.url") String gridUrl();
    @Key("bs.username") String bsUsername();
    @Key("bs.accessKey") String bsAccessKey();

    // Appium
    @Key("appium.server") @DefaultValue("http://127.0.0.1:4723") String appiumServer();

    // Android
    @Key("android.udid") @DefaultValue("emulator-5554") String androidUdid();
    @Key("android.platformVersion") @DefaultValue("14") String androidPlatformVersion();
    @Key("android.deviceName") @DefaultValue("Android") String androidDeviceName();
    @Key("android.autoGrantPermissions") @DefaultValue("true") boolean androidAutoGrantPermissions();
    @Key("android.chromeArgs") @DefaultValue("--disable-fre --no-first-run") String androidChromeArgs();

    // iOS
    @Key("ios.udid") @DefaultValue("auto") String iosUdid();
    @Key("ios.platformVersion") @DefaultValue("17.5") String iosPlatformVersion();
    @Key("ios.deviceName") @DefaultValue("iPhone") String iosDeviceName();
    @Key("ios.bundleId") @DefaultValue("com.example.demo") String iosBundleId();
    @Key("ios.autoAcceptAlerts") @DefaultValue("true") boolean iosAutoAcceptAlerts();

    // Mobile Web
    @Key("mobile.web.startUrl") @DefaultValue("https://example.org") String mobileWebStartUrl();

    // Timeouts
    @Key("implicit.timeout") @DefaultValue("0") int implicitTimeout();
    @Key("explicit.timeout") @DefaultValue("15") int explicitTimeout();
    @Key("page.load.timeout") @DefaultValue("30") int pageLoadTimeout();

    // Retries
    @Key("retry.count") @DefaultValue("1") int retryCount();
}
