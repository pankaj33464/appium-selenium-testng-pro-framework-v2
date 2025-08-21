package framework.pages.mobile.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExampleHomePage extends BaseMobileWebPage {

    @FindBy(css = "h1")
    private WebElement heading;

    public ExampleHomePage open(String url) {
        driver.get(url);
        return this;
    }

    public String headingText() {
        return heading.getText();
    }
}
