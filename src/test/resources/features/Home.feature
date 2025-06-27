@DIA @home
Feature: UI and Functionality of HOME page

  @skipLaunch @logo
  Scenario: Verify the logo is displayed in the top-left corner
    Given The user is on the DIA homepage
    Then The DIA logo should be visible in the top-left corner

  @skipLaunch @navbar_items
  Scenario: Verify the navigation bar items are present
    Given The user is on the DIA homepage
    Then navigation list from are displayed from "home" and "NAV_01"

  @skipLaunch @navbar_nav
  Scenario Outline: Verify navigated page title after clicking nav link
    Given The user is on the DIA homepage
    When The user clicks the navigation link "<NavList>"
    Then The page title should be "<ExpectedTitle>"

    Examples: 
      | NavList | ExpectedTitle        |
      | Home    | DIA                  |
      | Try Now | Continue with Google |
      | Pricing | Continue with Google |
      | Login   | Continue with Google |

  @skipLaunch @btn_nav
  Scenario Outline: Verify Click CTA buttons
  #  Given The user is on the DIA homepage
   # When The user clicks the button "<ButtonText>"
   # Then The page title should be "Continue with Google"

   
   # Examples: 
   #   | ButtonText   |
    #  | Try for free |

  # | For Dietitians   |
  
 
    
    
    
    
    
    
    
    
    
    
