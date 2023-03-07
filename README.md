Salesman Kotlin Spring Boot Application

This repository contains a sample Kotlin Spring Boot Application that showcases various features of Spring Boot.

Prerequisites
  JDK 11 or later
  Gradle 6.0 or later
  
Getting Started

To get started with the application, clone the repository and run the following command:

```
create postgres database "salesman" with user "salesman";
  psql -> CREATE ROLE salesman with password "password" "superadmin";
           CREATE DATABASE salesman with password "password";
           GRANT ALL PRIVILEGES ON salesman TO 'salesman';
           
```

To run the project 

  ./gradlew build
  ./gradlew bootRun
  
This will start the Spring Boot application on port 8080.

API Endpoints

POST /graphql: 

mutation CreatePayment($paymentRequest: PaymentRequest!) {
  createPayment(request: $paymentRequest) {
      finalPrice
      points
  }
}

{
    "paymentRequest" : {
        "price": "100.00",
        "priceModifier": 0.95,
        "paymentMethod": "MASTERCARD",
        "datetime": "2023-03-07T13:35:30.00"
    }
}

----------------------------------------------------

query GetSalesData($salesDataRequest: SalesDataRequest!) {
  getSalesData(request: $salesDataRequest) {
      sales {
          dateTime
          sales
          points
      }
  }
}

{
    "salesDataRequest" : {
        "startDateTime": "2023-03-07T00:00:00.00",
        "endDateTime": "2023-03-06T23:59:59.00"
    }
}
