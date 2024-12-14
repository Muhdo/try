package Utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static BaseClasses.TestBase.driver;

public class ExtentReport implements ITestListener {

ExtentSparkReporter extentSparkReporter;
ExtentReports extentReports;
ExtentTest test;


    public void onStart(ITestContext context){

        String reportPath = "./Reports/TestReport.html";
        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Automation report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Tester", "Muhammed");
        extentReports.setSystemInfo("Browser name", "chrome");
        extentReports.setSystemInfo("OS", "Windows 11");

    }

    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS, "Test case passed is: " + result.getName());
    }

    public void onTestFailure(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, "Test case failed is: " + result.getName());

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "./Screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            test.addScreenCaptureFromPath(screenshotPath, "Screenshot of the failure");
        }catch (IOException e){
            e.getMessage();
        }
    }

    public void onTestSkipped(ITestResult result){

    }

    public void onFinish(ITestContext context){
extentReports.flush();
    }

}
