
Feature: Login feature
  Scenario: R001 Valid login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on HomePage