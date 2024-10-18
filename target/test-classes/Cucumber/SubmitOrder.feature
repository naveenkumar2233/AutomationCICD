
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I Landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples: 
      | name             | password    | productName  |
      | shetty@gmail.com | Iamking@000 | ZARA COAT 3  |
      
