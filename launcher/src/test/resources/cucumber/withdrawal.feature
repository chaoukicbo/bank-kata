Feature: In order to retrieve some or all of my savings
         As a bank client
         I want to make a withdrawal from my account

  Scenario: as a bank client i want to make a withdrawal from my account
    Given the following client number 222222
    And with the following account number 2121212
    When I make a withdrawal in my account with an amount of 150.5
    Then my account balance will be equal to 499.5

  Scenario: as a bank client i want to make a withdrawal from my account
    Given the following client number 222222
    And with the following account number 2121212
    When I make a withdrawal in my account with an amount of 50.55
    Then my account balance will be equal to 448.95

  Scenario: as a client i want to make a withdrawal from my account with an amount greater than my account balance
    Given the following client number 222222
    And with the following account number 2121212
    When I try to make a WITHDRAWAL in my account with an amount of 500.0
    Then i will have the following exception InsufficientFunds with the following message : 'Insufficient Funds in your account : your account balance is 448.95'

  Scenario: as a client i want to make a withdrawal from my account with an amount less or equal to zero (0.0)
    Given the following client number 222222
    And with the following account number 2121212
    When I try to make a WITHDRAWAL in my account with an amount of 0.0
    Then i will have the following exception AmountException with the following message : 'amount must be greater than : 0.0'

  Scenario: as a client i want to make a withdrawal from my account with an amount greater than authorized
    Given the following client number 222222
    And with the following account number 2121212
    When I try to make a WITHDRAWAL in my account with an amount of 100000.0
    Then i will have the following exception AmountException with the following message : 'amount must be less or equal to : 2000.0'

  Scenario: as a client i want to make a withdrawal from my account using a wrong client number
    Given the following wrong client number 9879878
    And with the following account number 1212121
    When I try to make a WITHDRAWAL in my account with an amount of 55.0
    Then i will have the following exception AccountNotFoundException with the following message : 'Account not found please check your account and client numbers'

  Scenario: as a client i want to make a withdrawal from my account using a wrong account number
    Given the following client number 111111
    And with the wrong following account number 555555
    When I try to make a WITHDRAWAL in my account with an amount of 55.0
    Then i will have the following exception AccountNotFoundException with the following message : 'Account not found please check your account and client numbers'