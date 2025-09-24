Feature: Compare PrivatBank API rates with UI rates

  Scenario Outline: Compare <ccy> buy/sale rates (API vs UI)
    Given I request PrivatBank public rates for "<ccy>" from API
    And I open PrivatBank main page
    When I read UI buy and sale rates for "<ccy>"
    Then API buy rate should equal UI buy rate within <delta>
    And API sale rate should equal UI sale rate within <delta>

    Examples:
      | ccy | delta |
      | USD | 0.5   |
      | EUR | 0.5   |