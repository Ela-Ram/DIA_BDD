package common;

import org.openqa.selenium.WebDriver;
import driverFactory.DriverConfig;
import pageFactory.Home_Page;

public class TestContext {
    private WebDriver driver;
    private DriverConfig factory;
    private Helper helper;
    private ExcelReader excelReader;
    private String baseUrl;
    private Home_Page home_page;

    public TestContext() {
        factory = new DriverConfig();
        excelReader = new ExcelReader(); 
        baseUrl = ConfigReader.getProperty("URL");
    }

    public WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("Browser");
            driver = factory.initializeDriver(browser);
        }
        return driver;
    }

    public Helper getHelper() {
        if (helper == null) {
            helper = new Helper(getDriver());
        }
        return helper;
    }

    public ExcelReader getExcelReader() {
        return excelReader;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

   public Home_Page getHome_Page() {
        if (home_page == null) {
        	home_page = new Home_Page(getDriver());
       }
       return home_page;
    }
   public void resetPages() {
	    home_page = null;
	   
	    // Add other page objects here as you create them
	}

}
