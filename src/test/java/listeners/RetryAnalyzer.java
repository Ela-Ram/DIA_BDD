package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import common.ConfigReader;
import common.LoggerLoad;


public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
   // private static final int MAX_RETRY_COUNT = 2;
    int MAX_RETRY_COUNT = ConfigReader.getIntProperty("RetryCount");

    @Override
    public boolean retry(ITestResult result) {
    	if(!result.isSuccess())
    		
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LoggerLoad.info("Retry for failed scenario "+retryCount);
           
            return true;
        }
    	
        return false;
    }

   
}