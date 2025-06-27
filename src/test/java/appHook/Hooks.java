package appHook;

import common.Helper;
import common.LoggerLoad;
import common.Screenshot;
import common.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 0)
    public void setUp() {
   
        common.ExcelReader.loadAllSheets();  // Load once before any scenario
      
    }
    
    @Before(order = 1)
    public void launchAppIfRequired(Scenario scenario) {

    	if (!Helper.isScenarioTaggedWith(scenario, "@skipLaunch")) {
    		context.getDriver().get(context.getBaseUrl());
    	}
    }
  
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
        	Screenshot.takeScreenshot(context.getDriver(),scenario);
            LoggerLoad.info("Screenshot captured for failed scenario: " + scenario.getName());
        }
        context.resetPages();
        context.getDriver().quit();
        LoggerLoad.info("Browser is closed");
    }
    
    
}
