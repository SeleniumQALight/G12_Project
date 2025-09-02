Feature: Login feature

  Scenario: R001 Valid login
    Given I open Login Page
    When I login with valid cred
    Then I see avatar on HomePage

  Scenario Outline: R002 Login with invalid cred
    Given I open Login Page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input PassWord in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password           |
      | qaauto          | not_valid_password |
      | not_valid_login | 123456qwerty       |
