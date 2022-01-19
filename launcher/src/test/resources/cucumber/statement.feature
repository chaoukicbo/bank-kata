Feature: Statement history : In order to check my operations
  As a bank client
  I want to see the history (operation, date, amount, balance) of my operations

  Scenario: as a bank client i want to make a deposit in my account
    Given the following client number 333333
    And with the following account number 4444444
    When I make a deposit in my account with an amount of 150.5
    Then my account balance will be equal to 1150.5

  Scenario: as a bank client i want to make a deposit in my account
    Given the following client number 333333
    And with the following account number 4444444
    When I make a deposit in my account with an amount of 250.5
    Then my account balance will be equal to 1401.0

  Scenario: as a bank client i want to make a withdrawal from my account
    Given the following client number 333333
    And with the following account number 4444444
    When I make a withdrawal in my account with an amount of 105.3
    Then my account balance will be equal to 1295.7
    And my history of operations will be the following statements
      | operation  | date       | amount | balance |
      | WITHDRAWAL | 2022-01-07 | 105.3  | 1295.7  |
      | DEPOSIT    | 2022-01-07 | 250.5  | 1401.0  |
      | DEPOSIT    | 2022-01-07 | 150.5  | 1150.5  |