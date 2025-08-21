package framework.tests.mobile.nativeapp;

import framework.core.BaseTest;
import framework.pages.mobile.nativeapp.CalculatorPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

@Epic("Mobile")
@Feature("Android Calculator")
public class SampleAndroidNativeTest extends BaseTest {

    @Test(groups = {"mobile_native"}, description = "2 + 2 = 4 on Android calculator", retryAnalyzer = framework.listeners.RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    public void twoPlusTwoShouldBeFour() {
        String platform = framework.core.ConfigManager.cfg().platform().toLowerCase();
        if (!platform.equals("android_native")) {
            throw new SkipException("Run with -Dplatform=android_native to execute this test");
        }
        String result = new CalculatorPage().twoPlusTwo().getResult();
        Assert.assertTrue(result.contains("4"), "Expected result to contain 4, got: " + result);
    }
}
