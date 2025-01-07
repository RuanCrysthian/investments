# Investment

This project is a resolution of a tech challenge (backend) from Coderockr enterprise.
Click [here](https://github.com/Coderockr/backend-test/tree/main) to see the complete description.

The developed project is a REST API for managing investments. The application allows storing and managing investments
while ensuring the application of specific business rules, such as compound gain calculation, taxation on profits during
withdrawals.

----------------

## Features

1. Investment Creation
    - Allows the creation of an investment by specifying an owner, a creation date, and an initial amount.
    - The creation date can be either today or a past date.
    - The investment amount cannot be negative.
2. Investment Viewing
    - Returns detailed information about an investment, including:
    - The initial amount invested.
    - The expected balance, which is calculated as the sum of the initial amount and the accumulated gains.
    - If the investment was already withdrawn, the balance reflects the gains up to the withdrawal date.
3. Investment Withdrawal
    - Supports full withdrawal of an investment (partial withdrawals are not allowed).
    - Withdrawals can occur either today or on a past date (as long as the date is after the investment creation date).
    - Taxes are automatically applied to the profit portion before returning the final withdrawal amount.
    - Withdrawals cannot be scheduled for future dates.

## Business Rules

1. Gain Calculation
    - Investments yield 0.52% per month, always on the same day of the month as the investment creation date.
    - Gains are compounded, meaning the gains from each period are added to the investment balance and used as the base
      for the next period’s gain calculation.
2. Taxation
    - Tax is applied only to the profit portion of the investment upon withdrawal.
    - The tax rate depends on the age of the investment:
    - Less than 1 year: 22.5% of the profit.
    - Between 1 and 2 years: 18.5% of the profit.
    - More than 2 years: 15% of the profit.

## Technologies Used

- **Language**: Java.
- **Framework**: Spring boot.
- **Database**: PostgreSQL.
- **Other libraries**:
    - JUnit
    - Mockito
    - Spring data
- **Docker**

----------------

## How to Run the Project

### Prerequisites

- Docker and Docker Compose

### Steps

1. Clone de repository:

```bash
git clone https://github.com/RuanCrysthian/investments.git
```

2. Navigate to the project folder:

```bash
cd investments
```

3. Run the application:

```bash
docker compose up -d
```

## API Documentation

The API was documented using Swagger. The complete documentation, including request and response examples, can be
accessed through the following link:

[Link to the API documentation](http://localhost:8080/swagger-ui/index.html#/)