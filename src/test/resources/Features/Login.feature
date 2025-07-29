Feature: Login

  Scenario Outline: Create an Account
    Given User Launch Chrome browser
    And User enters URL "<url>"
    And User clicks on Create An Account button
    When User enters First Name as "<firstName>" and Last Name as "<lastName>"
    And User enters Email as "<email>"
    And User enters password as "<password>"
    And User enters Confirm Password as "<confirmPassword>"
    Then User clicks on Create Account button
    Then Home Page Title should be "<homePageTitle>"
    Then close browser
    Examples:
      | url    | firstName    | lastName    | email    | password    | confirmPassword    | homePageTitle|
      | ${url} | ${firstName} | ${lastName} | ${email} | ${password} | ${confirmPassword} | ${homePageTitle}|