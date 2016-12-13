Feature: To ensure that a user can add items to cart

  #Background: I am on "http://sprint.spreeza.net/"
  @AddItemToCart
  Scenario: Add an item to cart and check totals
    Given: I am on "http://sprint.spreeza.net/"

    When I select a category
    And I select a random product
    And I select a size
    And I click on the Add to Cart button
    Then I check that totals are populated correctly

