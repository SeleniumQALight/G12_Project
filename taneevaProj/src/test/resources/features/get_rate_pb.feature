Feature: PrivateBank Exchange Rate Verification

  @R0010
  Scenario Outline: R0010 Compare API and UI exchange rates for selected currency
    Given I get exchange rate for <currency> by API
    When I get exchange rate for <currency> from the website
    Then I compare both exchange rates for <currency>

    Examples:
      | currency |
      | USD      |
      | EUR      |
      | PLN      |