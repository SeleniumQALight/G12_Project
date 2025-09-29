@ui @api @privat @rates
Feature: Compare PrivatBank exchange rates between API and UI




  # Перевіряємо купівлю/продаж для декількох валют
  Scenario Outline: API and UI rates must match
    Given I set PrivatBank API base url "https://api.privatbank.ua"
    When I fetch "<ccy>" "<side>" rate from Privat API coursid "5"
    And I open PrivatBank home page
    And I read "<ccy>" "<side>" rate from UI
    Then API and UI rates for "<ccy>" "<side>" should be equal within "<tolerance>"

    #Tolerance - зазор на округлення
    Examples:
      | ccy | side  | tolerance |
      | USD | buy   | 0.01      |
      | USD | sale  | 0.01      |
      | EUR | buy   | 0.01      |
      | EUR | sale  | 0.01      |
