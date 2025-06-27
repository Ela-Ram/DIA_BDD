package common;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class Helper {
	private WebDriver driver;
    private WebDriverWait wait;
    public Helper(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    
    public void clickElementByText(String buttonText) throws ElementClickInterceptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = driver.findElements(By.xpath("//a[contains(@class, 'btn')]"))
            .stream()
            .filter(el -> el.getText().trim().equalsIgnoreCase(buttonText))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Button with text '" + buttonText + "' not found"));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // Wait until the element is clickable
        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            // Try clicking again using JavaScript as fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


  /*  // Java Streams-  Click an element based on visible text
    public static void clickElementByText(List<WebElement> elements, String targetText) {
        elements.stream()
                .filter(el -> el.getText().trim().equalsIgnoreCase(targetText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element not found with text: " + targetText))
                .click();
    }*/
}
