package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class landingPage extends PageBase {
    public landingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "")
    private WebElement btn;
}
