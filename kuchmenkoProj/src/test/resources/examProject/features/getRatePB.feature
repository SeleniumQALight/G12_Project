Feature: PrivateBank Exchange Rate Verification

  Scenario Outline: Compare API and UI exchange rates for selected currency
    Given I request exchange rate for <currency> using API
    When I fetch exchange rate for <currency> from the UI
    Then I validate API and UI exchange rates for <currency>

    Examples:
      | currency |
      | EUR      |
      | USD      |