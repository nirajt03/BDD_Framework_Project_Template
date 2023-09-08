@Regression @Smoke
Feature: Course Page Functionality for Pluralsight Clone Application

  Background: 
    Given User launches the Pluralsight Clone Application using link ""

	@Custom
  Scenario Outline: To verify Course Page Features
     Given User login as "<Authority>"
    And Click on Course tab in Nav Bar List
    Given Select required course from filtered course list
    When Move to Course page
    Then Verify selected Course header text as "<Course Header text>"
    Then Verify selected Course description test as "<Course Description text>"
    And Validate other course details as "<Author Name>", "<Free Trail text>" and "<Course Overview text>"
    And Logout from Pluralsight clone application

    Examples: 
      | Authority | Course Header text | Course Description text | Author Name | Free Trail text | Course Overview text |
      | Admin     |                    |                         |             |                 |                      |
      | User      |                    |                         |             |                 |                      |
