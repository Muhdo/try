package Tests;

import BaseClasses.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class testing extends TestBase {

    @Test(priority = 1, description = "Validate user is able to navigate to the site")
    public void TC_01(Method method){
       // initializer();
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(testData.getProperty("url"), "https://www.saucedemo.com/");
        sleep(10);
    }
}
