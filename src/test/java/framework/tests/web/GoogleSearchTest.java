package framework.tests.web;

import framework.core.BaseTest;
import framework.core.ConfigManager;
import framework.pages.web.GoogleHomePage;
import framework.pages.web.GoogleResultsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Web")
@Feature("Google Search")
public class GoogleSearchTest extends BaseTest {

    @Test(groups = {"web"}, description = "Verify Google search shows results", retryAnalyzer = framework.listeners.RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    public void googleSearchShouldWork() {
        String baseUrl = ConfigManager.cfg().webBaseUrl();
        GoogleResultsPage results = new GoogleHomePage()
                .open(baseUrl)
                .search("Appium Selenium TestNG framework");

        Assert.assertTrue(results.resultsLoaded(), "Expected results to be visible");
    }
}
