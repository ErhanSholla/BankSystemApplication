# Bank System Project

This project is designed to manage a banking system with a focus on creating new banks and their associated details.

## Installation and Setup

### Database Setup
- Install PostgreSQL.
- Create a database named `banksystem`.

### Project Configuration
- Clone this repository.

### Running the Application
- Build and run the project using your preferred IDE or Maven.

## API Endpoints

### Create a Bank

- **Endpoint**: `POST /banks`
- **Description**: Creates a new bank with specified details.
- **Request Body**:
  ```json
  {
    "name": "Bank Name",
    "transactionfee": 0.0,
    "transferamount": 0.0,
    "transactionflatfee": 0.0,
    "transactionpercentfee": 0.0
  }
  
- `name` (String, required): Name of the bank.
- `transactionfee` (double, optional): Transaction fee (if specified, either `transferamount` or both `transactionflatfee` and `transactionpercentfee` are required).
- `transferamount` (double, optional): Transfer amount (if specified, both `transactionflatfee` and `transactionpercentfee` are required).
- `transactionflatfee` (double, required): Flat transaction fee (non-negative value).
- `transactionpercentfee` (double, required): Percentage transaction fee (between 0 and 100 inclusive).

### Responses

- `201 Created`: Bank created successfully.
- `409 Conflict`: Bank with the same name already exists.

### Example

```http
POST /banks
Content-Type: application/json

{
  "name": "MyBank",
  "transactionfee": 0.0,
    "transferamount": 0.0,
    "transactionflatfee": 5.0,
    "transactionpercentfee": 2.5
}
```
### Create a Account
### Endpoint

`POST /v1/api/account`

### Description

Create a new account within a specified bank.

### Request Body

```json
{
  "name": "Account Holder Name",
  "balance": 0.0,
  "bankName": "Bank Name"
}
```

- `name` (String, required): Name of the account holder.
- `balance` (double, required): Initial balance (must be more than 0).
- `bankName` (String, required): Name of the bank associated with the account.

### Responses
- `201 Created`:Account created successfully.
- `400 Bad Request`: Invalid request body or missing required fields.
- `404 Not Found`: Bank specified in bankName not found.

### Perform Transactions
## Endpoint
`POST /v1/api/transaction/save`

### Description
Save a new transaction between accounts.

### Request body
```json
{
  "sentId": 1,
  "receiverId": 2,
  "amount": 100.0
}
```
- `senderAccountId` (long, required): ID of the sending account.
- `receiverAccountId` (long, required): ID of the receiving account.
- `amount` (double, required): Amount to transfer between accounts.

### Responses
- `201` Created: Transaction saved successfully.
- `400` Bad Request: Invalid request body or missing required fields.
`404` Not Found: Sender or receiver account not found.

### Withdraw Money
- `Endpoint`: POST /v1/api/account/{accountId}/withdraw={amount}
- `Description`: Withdraw a specified amount from an account.
### Deposit Money
- `Endpoint`: POST /v1/api/account/{accountId}/deposit={amount}
- `Description`: Deposit a specified amount into an account.
### Get Transactions by Account ID
- `Endpoint`: GET /v1/api/transaction/{id}
- `Description`: Retrieve a list of transactions for a specific account.
### Check Balance
- `Endpoint`: GET /v1/api/account/balance/{id}
- `Description`: Check the balance of a specific account.
### List All Banks
- `Endpoint`: GET /v1/api/bank
- `Description`: Retrieve a list of all registered banks.
### Check Total Transaction Fee Amount
- `Endpoint`: GET /v1/api/bank/totaltransactionfee/{id}
- `Description`: Calculate and return the total transaction fee amount for a bank.
### Check Bank Total Transfer Amount
- `Endpoint`: GET /v1/api/bank/totaltransferamount/{id}
- `Description`: Calculate and return the total transfer amount for a bank.
### Error Handling
- Requests that contain invalid parameters will result in appropriate error responses.

## Technologies Used
Java
Spring Boot
PostgreSQL
