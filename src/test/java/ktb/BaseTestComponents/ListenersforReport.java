package ktb.BaseTestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import kt.resources.ExtentReporterNG;

public class ListenersforReport extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	//ThreadLocal extentTest = new ThreadLocal(); // Threadsafe
	//threadsafe means if we run concurrently, each object creation have its own thread, so it won't interrupt the 
	// other overriding variable.
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());// everytime test start it will create a resport pf result
		extentTest.set(test); //assign unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test Passed");  // evertime test pass then
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		//test.log(Status.FAIL, "Test Failed");  // whenever test fails then
		extentTest.get().fail(result.getThrowable()); // when test fails it will show the errormessage of that fail case.
		try {
			d1 = (WebDriver) result.getTestClass().getRealClass().getField("d1").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// attache a screenshot to the report
		//String filePath = null;
		String filePath ="";
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),d1).getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (filePath != "") {
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); //give argument where ss stored and its title
		}				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}
	

}
