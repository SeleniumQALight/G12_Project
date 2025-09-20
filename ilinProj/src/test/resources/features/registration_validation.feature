Feature: Registration form validation



  Scenario Outline: Show correct error messages for invalid registration data
    Given I open the Login page
    When I fill the registration form with "<username>", "<email>", "<password>"
    Then I should see registration validation errors: "<errors>"

    Examples:
      | username | email          | password       | errors                  |
      | tr       | tr1            | tr2            | USERNAME,EMAIL,PASSWORD |
      | artem    | tr1            | tr2            | EMAIL,PASSWORD          |
      | -        | -              | -              | USERNAME,EMAIL,PASSWORD |
      | tr       | a@a            | StrongPass123! | USERNAME                |
      | artem    | not-an-email   | StrongPass123! | EMAIL                   |
      | artem    | test@email.com | short          | PASSWORD                |
