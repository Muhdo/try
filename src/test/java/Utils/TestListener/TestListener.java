package Utils.TestListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

public void onStart(ITestContext context){
    System.out.println("Test execution is starting: " + context.getName());
}

public void onFinish(ITestContext context){

}

public void onTestSuccess(ITestResult result){
    System.out.println("Test passed: " + result.getMethod().getMethodName());
}

public void onTestFailure(ITestResult result){

}

public void onTestSkipped(ITestResult result){

}

public void onTestStart(ITestResult result){

}
}
