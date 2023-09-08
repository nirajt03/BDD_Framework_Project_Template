@Regression @Smoke
Feature: Login Functionality for Pluralsight Clone Application

  Background: 
    Given User launches the Pluralsight Clone Application using link "<URL>"

  @Custom
  Scenario Outline: To verify login functionality
    Given Login form should be visible on launch application URL
    When User login as "<Authority>"
    Then Verify Search page has displayed after logging in
    And Logout from Pluralsight clone application

    Examples: 
      | Authority |
      | Admin     |
      | User      |

  @Custom
  Scenario Outline: To verify negative login functionality
    When Login form should be visible on launch application URL
    When User should enter credentials as "<Username>" and "<Password>"
    Then Verify login error message as "<Error Message>"
    And Logout from Pluralsight clone application

    Examples: 
      | Username                    | Password | Error Message |
      | Invalid Username            |          |               |
      | Invalid Password            |          |               |
      | Missing Username            |          |               |
      | Missing Password            |          |               |
      | Missing Username & Password |          |               |
