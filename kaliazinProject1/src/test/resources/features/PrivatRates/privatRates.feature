@R001
Feature: Compare PrivatBank API vs UI exchange rates

  Scenario Outline: Compare <currency> rate from API and UI
    Given I get "<currency>" rate from PrivatBank API
    And I open PrivatBank homepage
    When I read "<currency>" rate from UI
    Then API and UI "<currency>" rates are equal with tolerance <tolerance>

    Examples:
      | currency | tolerance |
      | USD      | 0.10      |
      | EUR      | 0.10      |