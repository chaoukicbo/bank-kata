Feature: In order to save money
         As a bank client
         I want to make a deposit in my account

  Scenario: as a bank client i want to make a deposit in my account
    Given the following client number 111111
    And with the following account number 1212121
    When I make a deposit in my account with an amount of 150.5
    Then my account balance will be equal to 250.5

  Scenario: as a bank client i want to make an other deposit in my account
    Given the following client number 111111
    And with the following account number 1212121
    When I make a deposit in my account with an amount of 55.0
    Then my account balance will be equal to 305.5

  Scenario: as a client i want to make a deposit in my account using a wrong client number
    Given the following wrong client number 9879878
    And with the following account number 1212121
    When I try to make a DEPOSIT in my account with an amount of 55.0
    Then i will have the following exception AccountNotFoundException with the following message : 'Account not found please check your account and client numbers'

  Scenario: as a client i want to make a deposit in my account using a wrong account number
    Given the following client number 111111
    And with the wrong following account number 555555
    When I try to make a DEPOSIT in my account with an amount of 55.0
    Then i will have the following exception AccountNotFoundException with the following message : 'Account not found please check your account and client numbers'


  Scenario: as a client i want to make a deposit in my account using an account number of an other client
    Given the following client number 111111
    And with the wrong following account number 2121212
    When I try to make a DEPOSIT in my account with an amount of 55.0
    Then i will have the following exception AccountNotFoundException with the following message : 'Account not found please check your account and client numbers'
