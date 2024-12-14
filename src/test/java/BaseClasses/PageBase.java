package BaseClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;



public class PageBase {


    WebDriverWait wait;
    int attempt;
    public static WebDriver driver;

    public void waitForElementToBeClickable(WebElement element){
      wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public PageBase(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void click(WebElement element){
         attempt = 0;
        while (attempt < 3) {
            try {
                waitForElementToBeClickable(element);
                element.click();
                break;
            }catch (StaleElementReferenceException e){
                //System.out.println(e.getMessage());
                attempt++;
            }
        }
        if(attempt == 3){
            throw new RuntimeException("Failed to click element after 3 attempts due to StaleElementReferenceException");
        }
    }

    public void enterText(WebElement element, String text){
        attempt = 0;
        while(attempt < 3){
            try{
                waitForElementToBeVisible(element);
                element.sendKeys(text);
                break;
            }catch (StaleElementReferenceException e){
                //System.out.println(e.getMessage());
                attempt++;
            }
        }
        if(attempt == 3){
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }
    public void clearText(WebElement element){
        waitForElementToBeVisible(element);
        element.clear();
    }

    public void scrollTo(WebElement element){
        attempt = 0;
        while(attempt < 3){
            try {
                waitForElementToBeVisible(element);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true)",element);
            }catch (StaleElementReferenceException e){
                attempt++;
            }
        }
        if(attempt == 3){
            throw new RuntimeException("Failed to scroll to element after 3 attempts due to StaleElementReferenceException");
        }

    }

    public void selectOption(WebElement element, int indexValue){
        attempt = 0;
        while (attempt < 3){
            try{
                Select select = new Select(element);
                select.selectByIndex(indexValue);
                break;
            }catch (StaleElementReferenceException e){
                attempt++;
            }
        }
        if(attempt == 3){
            throw new RuntimeException("Failed to select element after 3 attempts due to StaleElementReferenceException");
        }
    }

    public void verifyText(WebElement element, String text, String message){
        attempt = 0;
        while(attempt < 3){
            try {
                waitForElementToBeVisible(element);
                Assert.assertEquals(element.getText(), text, message);
                break;
            }catch (StaleElementReferenceException e){
                attempt++;
            }
        }
    }
}
