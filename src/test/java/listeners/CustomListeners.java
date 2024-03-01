package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {

        Reporter.log("Capturing a screenshot");
        Reporter.log("<a target=\'_blank' href='C:/Users/Beni/Downloads/1676889880009.jpg'>Screenshot</a>");//target=\'_blank' is to open the link in another window

        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestSuccess(result);
    }

}
