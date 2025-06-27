package pageFactory;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class Home_Page {
	
	 WebDriver driver;
	 
	 @FindBy(className ="navbar-logo") WebElement diaLogo;
	 @FindBy(css = "nav a") List<WebElement> navList;
	 @FindBy(xpath = "//a[contains(@class, 'btn')]") List<WebElement> navButtons;

	 
	 @FindBy(className ="auth-provider-text") WebElement loginGoogle;
	 @FindBy(xpath = "//a[@class='modern-brand']/span[text()='DIA']") WebElement homeTextDIA;
	 
	// //a[@class='modern-brand']/span[text()='DIA']
	 
	// @FindBy(xpath = "//a[contains(@class, 'modern-nav-link')]") List<WebElement> navList;
	

	
	 
	 public Home_Page(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	 
	 public boolean isLogoVisible() {
		    return diaLogo.isDisplayed();
		}
	 
	 
	 public List<String> getNavLinkTexts() {
		    return Helper.getTexts(navList);
		}
	 
	 public void clickNavByText(String linkText) {
		    Helper.clickElementByText(navList, linkText);
		}

		public List<String> getNavBtnTexts() {
		    return Helper.getTexts(navButtons);
		}

		public void clickNavButton(String btn) {
		   Helper.clickElementByText(navButtons, btn);
		}

	 
/*	 public List<String> getNavLinkTexts() {
		    return navList.stream()
		                   .map(WebElement::getText)
		                   .collect(Collectors.toList());
		}
	 public void clickNavByText(String linkText) {
	        navList.stream()
	               .filter(el -> el.getText().trim().equalsIgnoreCase(linkText))
	               .findFirst()
	               .orElseThrow(() -> new RuntimeException("Nav link not found: " + linkText))
	               .click();
	    }
	 
	 public List<String> getNavBtnTexts() {
		    return navButtons.stream()
		                   .map(WebElement::getText)
		                   .collect(Collectors.toList());
		}
	 
	 public void clickNavButton(String btn) {
		 navButtons.stream()
	               .filter(el -> el.getText().trim().equalsIgnoreCase(btn))
	               .findFirst()
	               .orElseThrow(() -> new RuntimeException("Button not found: " + btn))
	               .click();
	    }
	*/ 
	 
	 public String homeTextDIAText() {
	        return homeTextDIA.getText(); 
	    }
	 
	 public String getGoogleLoginText() {
	        return loginGoogle.getText(); // e.g., "Continue with Google"
	    }
	 
	
}
