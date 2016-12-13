Feature: To ensure that the Spree websites are accessible to users

@OpenWebsite
  Scenario: Navigate to live website
    Given I am on "http://www.spree.co.za/"
    Then I check that the page title is visible

@OpenWebsite
  Scenario: Navigate to staging environemnt one website
    Given I am on "http://sprint.spreeza.net/"
    Then I check that the page title is visible

@OpenWebsite
  Scenario: Navigate to staging environemnt two website
    Given I am on "http://magento.spreeza.net/"
    Then I check that the page title is visible

@OpenWebsite
  Scenario: Navigate to regression environemnt website
    Given I am on "http://elastic.spreeza.net/"
    Then I check that the page title is visible
