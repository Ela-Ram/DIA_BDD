package stepDefinitions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import common.ExcelReader;
import common.Helper;
import common.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.Home_Page;

public class Home_Step {

	private TestContext context;
	
	private  Home_Page home_page;

	public Home_Step(TestContext context) {
        this.context = context;
    
        this.home_page = context.getHome_Page();
    }
	
	@Given("The user is on the DIA homepage")
	public void the_user_is_on_the_dia_homepage() {
		context.getDriver().get(context.getBaseUrl());
	  
	}

	@Then("The DIA logo should be visible in the top-left corner")
	public void the_dia_logo_should_be_visible_in_the_top_left_corner() {
		Assert.assertTrue(home_page.isLogoVisible());
	}
	
	@Then("navigation list from are displayed from {string} and {string}")
	public void navigation_list_from_are_displayed_from_and(String sheetName, String testCaseId) {
		Map<String, String> testData = ExcelReader.getTestData(sheetName, testCaseId);
		 String Links = testData.get("NavLinks");
		// List<String> expected = Arrays.asList(Links.split(","));
		 
		 List<String> expected = Arrays.stream(Links.split(","))
                 .map(String::trim)
                 .filter(s -> !s.isEmpty()) 
                 .toList();

		 List<String> actual = home_page.getNavLinkTexts();

		 System.out.println("Expected: " + expected);
		 System.out.println("Actual  : " + actual);

		 Assert.assertTrue(actual.containsAll(expected), "Mismatch in navigation links");

	}

	@When("The user clicks the navigation link {string}")
	public void the_user_clicks_the_navigation_link(String NavList) {
		home_page.clickNavByText(NavList);
		
	    
	}

	@Then("The page title should be {string}")
	public void the_page_title_should_be(String expectedText) {
		
		if (expectedText.equalsIgnoreCase("DIA")) {
	        // For Home page, check Google login button text
			String actualText = home_page.homeTextDIAText();
	    	 System.out.println("Expected Title: " + expectedText);
			 System.out.println("Actual Title  : " + actualText);
		    Assert.assertEquals(actualText, expectedText, "Page  does not match after navigation.");

	    } else {
	        // For all other pages, check title
	    	 String actualText = home_page.getGoogleLoginText();
	    	 System.out.println("Expected Title: " + expectedText);
			 System.out.println("Actual Title  : " + actualText);
		    Assert.assertEquals(actualText, expectedText, "Page does not match after navigation.");
	    }
		
	}
	@When("The user clicks the button {string}")
	public void the_user_clicks_the_button(String button) {
		home_page.clickNavButton(button);
	    
	}

	@When("The user presses the Tab key repeatedly")
	public void the_user_presses_the_tab_key_repeatedly() {
	    
	}

	@Then("Focus should shift logically between interactive elements")
	public void focus_should_shift_logically_between_interactive_elements() {
	   
	}

	
}
