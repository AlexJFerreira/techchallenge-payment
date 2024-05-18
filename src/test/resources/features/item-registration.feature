Feature: the payment creation endpoint
  Scenario: client makes call to POST payment creation
    Given client wants to create a payment
    When client calls payment creation endpoint
    Then client receives payment creation confirmation