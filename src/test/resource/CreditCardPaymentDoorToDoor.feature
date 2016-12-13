Feature: To ensure that a user can use the credit card payment
    
      #Background: I am on "http://sprint.spreeza.net/"
  @CreditCardPaymentDoorToDoor
  Scenario: Add an item to cart and checkout with credit card payment option
    Given: I am on "http://sprint.spreeza.net/"

    When I add item to cart
    And I register
    And I enter shipping address
    And I select door to door shipping method
    And I select credit/debit card payment option
    And I place an order
    Then I check that the order is listed on the gridview
    
