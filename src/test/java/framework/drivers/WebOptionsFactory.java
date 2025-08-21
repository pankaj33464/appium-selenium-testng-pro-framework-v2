package framework.drivers;

import framework.core.ConfigManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class WebOptionsFactory {
    public static Object create() {
        String browser = ConfigManager.cfg().webBrowser().toLowerCase();
        switch (browser) {
            case "chrome" -> {
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--remote-allow-origins=*");
                opts.addArguments("--disable-gpu");
                opts.addArguments("--window-size=1920,1080");
                return opts;
            }
            case "firefox" -> {
                return new FirefoxOptions();
            }
            case "edge" -> {
                return new EdgeOptions();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
