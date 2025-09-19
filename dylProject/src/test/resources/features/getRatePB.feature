Feature: Get Rate Private Bank feature

  @R004
  Scenario Outline: R004 Compare the rate received by API with the rate received by UI
    Given I get exchange rate for <currency> by API
    When I get exchange rate for <currency> from the website
    Then I compare both exchange rates for <currency>

    Examples:
      | currency |  |
      | EUR      |  |
      | USD      |  |
