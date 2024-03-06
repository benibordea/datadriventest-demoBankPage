package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;
import utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener {

	public 	String messageBody;
	public void onTestFailure(ITestResult arg0) {

		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().log(LogStatus.FAIL, "Test failed");

		String screenshotPath = ("user.dir") + "\\target\\surefire-reports\\html\\" + TestUtil.screenshotName; // Update this with the path to your screenshot
        TestBase.test.get().log(LogStatus.FAIL, "Snapshot below: " + TestBase.test.get().addScreenCapture(TestUtil.screenshotName));


		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");

		
	}

	


	@Override
    public void onTestStart(ITestResult result) {
        test.set(extent.startTest(result.getMethod().getMethodName()));
    }

	@Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(LogStatus.PASS, "Test passed");
    }
	
}


