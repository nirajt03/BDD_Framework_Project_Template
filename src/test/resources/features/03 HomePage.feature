@Regression @Smoke
Feature: Home Page Functionality for Pluralsight Clone Application

  Background: 
    Given User launches the Pluralsight Clone Application using link ""
   
   @Custom
  Scenario Outline: To verify home page features
    Given User login as "<Authority>"
    And User needs to navigate to home page
    When Home Page must be visible to user 
    Then Verify Home page header text
    Then  Verify Home page description text
    And Logout from Pluralsight clone application

    Examples: 
      | Authority |
      | Admin     |
      | User      |

