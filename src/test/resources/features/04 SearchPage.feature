@Regression @Smoke
Feature: Search Page functionalities for Pluralsight Clone Application

  Background: 
    Given User launches the Pluralsight Clone Application using link ""


  Scenario Outline: To verify home page features
    Given User login as "<Authority>"
    And Search required course in search box
    Then Verify selected filter tabs options
    And Click on Course tab in Nav Bar List
    Then Verify filtered courses list details
    Then Verify selected Course description test as "<Course Description text>"
    And Validate other tabs details in nav bar list
    Then Logout from Pluralsight clone application

    Examples: 
      | Authority |
      | Admin     |
      | User      |

 @Custom
  Scenario Outline: To verify search Java course functionality
    Given User login as "<Authority>"
    And Search required course in search box
    Then Click on Course tab in Nav Bar List
    Then Verify filtered courses list details
    Given Select required course from filtered course list
    When Move to Course page
    Then Verify selected Course header text as "<Course Header text>"
    Then Verify selected Course description test as "<Course Description text>"
    Then Logout from Pluralsight clone application

    Examples: 
      | Authority |
      | Admin     |
      | User      |
