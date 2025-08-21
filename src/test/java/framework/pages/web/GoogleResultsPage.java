package framework.pages.web;

import framework.utils.Waits;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleResultsPage extends BaseWebPage {

    @FindBy(css = "div#search")
    private WebElement results;

    public boolean resultsLoaded() {
        return Waits.visible(results).isDisplayed();
    }
}
