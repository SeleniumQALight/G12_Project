Feature: Validation messages feature

  Scenario Outline: R005 Check validation messages
    Given I open Login Page
    When I enter '<userName>' into input registrationUsername in Login page
    And I enter '<email>' into input registrationEmail in Login page
    And I enter '<password>' into input registrationPassword in Login page
    Then I check '<validationMessages>'

    Examples:
      | userName | email          | password     | validationMessages                                                                                                       |
      | a1       | a2             | a3           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | zevs     | a2             | a3           | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | hector   | a2             | hector123456 | This username is already taken.;You must provide a valid email address.                                                  |
      | zevs     | email@test.com | zevs123456   | Password must be at least 12 characters.                                                                                 |