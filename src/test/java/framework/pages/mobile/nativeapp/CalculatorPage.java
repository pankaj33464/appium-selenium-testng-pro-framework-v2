package framework.pages.mobile.nativeapp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage extends BaseNativePage {

    @FindBy(id = "com.android.calculator2:id/digit_2")
    private WebElement digit2;

    @FindBy(id = "com.android.calculator2:id/op_add")
    private WebElement add;

    @FindBy(id = "com.android.calculator2:id/eq")
    private WebElement equal;

    @FindBy(id = "com.android.calculator2:id/result")
    private WebElement result;

    public CalculatorPage twoPlusTwo() {
        digit2.click(); add.click(); digit2.click(); equal.click();
        return this;
    }

    public String getResult() { return result.getText(); }
}
