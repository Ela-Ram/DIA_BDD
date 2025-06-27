package testRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import common.ConfigReader;
import common.LoggerLoad;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
       // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "json:target/CucumberReports/DIAJson.json",
        "html:target/CucumberReports/DIA.html",
        "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
    },
    monochrome = false,
    tags = "@DIA",
    features = {"src/test/resources/features"},
    glue = {"stepDefinitions", "appHook"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeTest
	@Parameters({"browser"})
	public void defineBrowser(String browser) {
	    LoggerLoad.info(browser + " is Running");
	    ConfigReader.setProperty("Browser", browser);
	}


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
