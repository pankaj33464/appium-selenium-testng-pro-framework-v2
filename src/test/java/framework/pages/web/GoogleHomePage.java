package framework.pages.web;

import framework.utils.Waits;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BaseWebPage {

    @FindBy(name = "q")
    private WebElement searchBox;

    public GoogleHomePage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public GoogleResultsPage search(String query) {
        Waits.visible(searchBox).sendKeys(query + "\n");
        return new GoogleResultsPage();
    }
}
