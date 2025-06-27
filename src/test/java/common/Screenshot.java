package common;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

public class Screenshot {

    public static void takeScreenshot(WebDriver driver, Scenario scenario) {
        if (driver == null) {
            LoggerLoad.warn("WebDriver instance is null. Cannot take screenshot.");
            return;
        }
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            String screenshotName = scenario.getName().replaceAll("[\\\\/:*?\"<>|]", "_") + ".png";
            File destinationFile = new File("screenshots/" + screenshotName);
            try {
                FileUtils.copyFile(screenshot, destinationFile);
                LoggerLoad.info("Screenshot saved to: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                LoggerLoad.error("Screenshot IOException: " + e.getMessage());
                e.printStackTrace();
            }
            // Attach screenshot to Cucumber report
            byte[] screenshotBytes = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", scenario.getName());
        } else {
            LoggerLoad.warn("Driver does not support taking screenshots.");
        }
    }
}
