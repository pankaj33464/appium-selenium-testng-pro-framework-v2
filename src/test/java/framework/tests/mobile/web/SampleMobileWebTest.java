package framework.tests.mobile.web;

import framework.core.BaseTest;
import framework.core.ConfigManager;
import framework.pages.mobile.web.ExampleHomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

@Epic("Mobile Web")
@Feature("Example.org")
public class SampleMobileWebTest extends BaseTest {

    @Test(groups = {"mobile_web"}, description = "Open example.org and validate h1", retryAnalyzer = framework.listeners.RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    public void exampleHeadingShouldAppear() {
        String platform = framework.core.ConfigManager.cfg().platform().toLowerCase();
        if (!platform.endsWith("_web")) {
            throw new SkipException("Run with -Dplatform=android_web or -Dplatform=ios_web to execute this test");
        }
        String url = ConfigManager.cfg().mobileWebStartUrl();
        String heading = new ExampleHomePage().open(url).headingText();
        Assert.assertTrue(heading.length() > 0, "Expected a non-empty heading");
    }
}
