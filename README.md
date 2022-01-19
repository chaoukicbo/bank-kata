# Bank Account KATA

Bank account kata Think of your personal bank account experience When in doubt, go for the simplest solution
Requirements

- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing

## User Stories

### US 1:

In order to save money As a bank client I want to make a deposit in my account

### US 2:

In order to retrieve some or all of my savings As a bank client I want to make a withdrawal from my account

### US 3:

In order to check my operations As a bank client I want to see the history (operation, date, amount, balance) of my
operations

## Solution

This is a Spring Boot project with an embedded database (h2), and jdk 11

To test the application use a Postman after running application or curl. Adding a new client is not implemented in this
solution. This table displays existing clients in the h2 database with their respective account number

| client number | account number | account balance |
|---------------|----------------|-----------------|
| 111111        | 1212121        | 100             |
| 111111        | 1313131        | 200             |
| 222222        | 2121212        | 650             |

### API

3 api are provided : deposit, withdrawal and history

### 1. deposit

```text
POST /api/v1/bank/operation/deposit
``` 

With Postman

```text
POST http://localhost:8080/api/v1/bank/operation/deposit
body {
    "clientNumber": 111111,
    "accountNumber": 1212121,
    "amount": 11
    }
``` 

With curl

```text
POST 
curl -X POST http://localhost:8080/api/v1/bank/operation/deposit
-H "Content-Type: application/json"
-d '{"clientNumber": 111111, "accountNumber": 1212121, "amount": 11}'   
``` 

### 2. withdrawal

```text
POST /api/v1/bank/operation/withdrawal
``` 

With Postman

```text
POST http://localhost:8080/api/v1/bank/operation/withdrawal
body {
    "clientNumber": 111111,
    "accountNumber": 1212121,
    "amount": 50.5
    }
``` 

With curl

```text
POST 
curl -X POST http://localhost:8080/api/v1/bank/operation/withdrawal
-H "Content-Type: application/json"
-d '{"clientNumber": 111111, "accountNumber": 1212121, "amount": 50.5}'   
``` 


### 3. history

```text
POST /api/v1/bank/operation/history
``` 

With Postman

```text
POST http://localhost:8080/api/v1/bank/operation/history
body {
    "clientNumber": 111111,
    "accountNumber": 1212121,
    }
``` 

With curl

```text
POST 
curl -X POST http://localhost:8080/api/v1/bank/operation/history
-H "Content-Type: application/json"
-d '{"clientNumber": 111111, "accountNumber": 1212121}'   
``` 
