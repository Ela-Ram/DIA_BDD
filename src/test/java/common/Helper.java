package common;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class Helper {
	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;
    
    
    public Helper(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
    }
	
    
    
 // Waits for the exact page title to match
    public void waitForTitle(String expectedTitle) {
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }
    
    //wait for visibility
    public WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
    }
    
    //wait for clickable
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    
 // Checks if the scenario is tagged with a specific tag
    public static boolean isScenarioTaggedWith(Scenario scenario, String tag) {
        return scenario.getSourceTagNames().contains(tag);
    }

   // Java Streams- Get all visible texts from a list of WebElements
    public static List<String> getTexts(List<WebElement> elements) {
        return elements.stream()
                       .map(WebElement::getText)
                       .map(String::trim)
                       .filter(text -> !text.isEmpty())
                       .collect(Collectors.toList());
    }
    
    

    // Java Streams-  Click an element based on visible text
    public static void clickElementByText(List<WebElement> elements, String targetText) {
        elements.stream()
                .filter(el -> el.getText().trim().equalsIgnoreCase(targetText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element not found with text: " + targetText))
                .click();
    }
}
