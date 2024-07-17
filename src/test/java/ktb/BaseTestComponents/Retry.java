package ktb.BaseTestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private int count = 0;
	private static final int maxTry = 1; // how many times want to rerun failed tests

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry)
		{
			count++;
			//System.out.println("Retrying test.Attempt#"+count+":Pass");
		   	return true;
		}
		else
		{
			//System.out.println("Retrying test.Attempt#"+count+":Fail");
			return false;
		}
		
		
	}
	
	

}
