package framework.core;

import framework.drivers.DriverFactory;
import framework.mocks.WireMockBootstrap;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    @Step("Start WireMock if enabled")
    public void beforeSuite() {
        WireMockBootstrap.startIfEnabled();
    }

    @AfterSuite(alwaysRun = true)
    @Step("Stop WireMock")
    public void afterSuite() {
        WireMockBootstrap.stopIfRunning();
    }

    @BeforeMethod(alwaysRun = true)
    @Step("Setup driver")
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Tear down driver")
    public void tearDown(ITestResult result) {
        DriverFactory.quitDriver();
    }
}
