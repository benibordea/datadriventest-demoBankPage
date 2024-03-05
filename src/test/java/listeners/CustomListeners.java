package listeners;

import java.io.IOException;

import org.apache.velocity.test.misc.Test;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtil;
import base.TestBase;
import utilities.TestUtil;


public class CustomListeners implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {

        System.setProperty("org.uncommons.reportng.escape-output","false"); //to open the screenshot link in the report
        Reporter.log("Capturing a screenshot");
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestSuccess(result);
    }

}
