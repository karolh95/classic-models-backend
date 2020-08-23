# ClassicModels

[![Current Version](https://img.shields.io/badge/version-0.1.0-green.svg)](https://github.com/karolh95/classic-models-backend) [![App on Heroku](https://img.shields.io/badge/demo-heroku-green)](https://google.com)

Spring Boot application for a retailer of scale models of classic cars.

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [Inspiration](#inspiration)
* [Installation information](#installation-information)
* [Database configuration](#database-configuration)
  * [Tables](#tables)
* [API Calls](#api-calls)
  * [CRUD](#crud)
  * [Reports](#reports)

## General info

TODO: general info

## Technologies

* **Java** 11
* **Spring Boot** 2.3.1.RELEASE
* **Lombok** 1.18.12
* **MapStruct** 1.4.0.Beta3
* **Hibernate Metamodel Generator** 5.4.20.Final

## Features

TODO: Features

* [x] MapStruct Mapper
* [x] Hibernate Validator
* [ ] Query creation from method names
* [x] JPA Criteria API

## Inspiration

This application is based on [MySQL Sample Database](https://www.mysqltutorial.org/getting-started-with-mysql/mysql-sample-database-aspx). The classicmodels database is a retailer of scale models of classic cars database. It contains typical business data such as customers, products, sales orders, sales order line items, etc.

## Installation information

Classic models is a Spring Boot application built using Maven. You can build a jar file and run it from the command line:

```console
git clone https://github.com/karolh95/classic-models-backend.git
cd classic-models-backend
./mvnw package
java -jar target/*.jar
```

The base address for each calls is: `localhost:8080`.

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```console
 ./mvnw spring-boot:run
 ```

## API calls

URIs relative to localhost:8080.

### **CRUD**

* **Customers**

    | Method | HTTP request | description |
    | - | - | - |
    | getAllCustomers | GET /customers/all | Gets details of all customers |
    | getCustomer | GET /customers/details/`ID` | Gets detail of customer with given `ID` |
    | saveCustomer | POST /customers/save | Save or updates customer |

    API calls for **Employees**, **Officess**, **Products**, **Productlines** and **Orders** are similar;

* **Payments**

    | Method | HTTP request | description |
    | - | - | - |
    | getAllPayments | GET /payments/all | Get details of all payments |
    | getPaymentsByCustomer | GET /employees/details/`customerNumber` | Gets detail of *employee* |
    | getPayment | GET /payments/details/`customerNumber`/`checkNumber` | Gets details of |
    | savePayment | POST /payments/save | Save / updates payment |

* **Order Details**

    | Method | HTTP request | description |
    | - | - | - |
    | getAllOrderDetails | GET /orderdetails/all | Get details of all order details |
    | getOrderDetailsByOrder | GET /orderdetails/details/`orderNumber` | Gets *order details* for given order |
    | getOrderDetail | GET /payments/details/`orderNumber`/`ProductCode` | Gets *order detail* of *product* in given *order* |
    | saveOrderDetails | POST /orderdetails/save | Save / update order details |

### Reports

* **Customer Reports**

    URIs relative to **localhost:8080/customer/report/**.

    | Method | HTTP request | description |
    | - | - | - |
    | contactsAsc | GET contacts/asc | Get all customer contact details with ascending order. |
    | contactsDesc | GET contacts/desc | Get all customer contact details with descending order. |
    | getDistinctState | GET states/all | Get all distinct states. |
    | getFirst5State | GET states/5 | Get 5 first distinct states. |
    | getDistinctStateCity | GET states/city | Get distinct state and city combination. |
    | getStatesByCountry | GET states/country/`country` | Get all states for given country. |
    | findByCountryAndState | GET country/`country`/state/`state` | Get customer names for given country and state. |
    | findByCountryStateAndCreditlimit | GET country/`country`/state/`state`/creditLimit/`creditLimit` | Get customer names for given country and state and with greater than given credit limit. |
    | findOr | GET creditLimit/`creditLimit`/countries/`country1`/`country2` | Get customer names with greater than given credit limit for given countries. |
    | findByPage | GET page/`page`/size/`size` | Get customer names and numbers using pagination. |
    | findNthLowestCreditLimit | GET creditLimit/lowest/`n` | Get customer with Nth lowest credit limit. |
    | findNthHighestCreditLimit | GET creditLimit/highest/`n` | Get customer with Nth highest credit limit. |
    | getSalesRep | GET salesReps | Get customers and their sales Rep number. |
    | getCustomersWithOrders | GET orders/true | Get customers with ordered orders. |
    | getCustomersWithoutOrders | GET orders/false | Get customers without ordered orders |

* **Employee Reports**

    URIs relative to **localhost:8080/employee/report/**.

    | Method | HTTP request | description |
    | - | - | - |
    | summary | GET summary | Get employee names and job titles. |
    | summaryOffice | GET jobTitle/`jobTitle`/officeCode/`officeCode` | Get employees with given job title **OR** with given office code. |
    | officeCodeBetween | GET officeCode/`low`/`high` | Get employees with the office code in the given range. |
    | lastNameContaining | GET lastName/`lastName` | Get employee names and job titles if their name contains specific text.  |
    | getEmployeesWithCustomersPayments | GET customer/payments | Get employee names and their customers payments. |
    | getEmployeesWithCustomersNumbers | customer/true | Get employees with assigned customers. |
    | getEmployeesWithoutCustomersNumbers | GET customer/false | Get employees without assigned customers. |
    | getEmployeesWithReportsTo | GET salesReps | Get customer names and their reporters names. |

* **Office Reports**

    URIs relative to **localhost:8080/office/report/**.

    | Method | HTTP request | description |
    | - | - | - |
    | findByCountries | GET countries/in/`country1`/`country2` | Get office codes, cities and phone numbers for offices in given countries. |
    | findByCountriesNot | GET countries/notIn/`country1`/`country2` | Get office codes, cities and phone numbers for offices not in given countries.  |

* **Order Reports**

    URIs relative to **localhost:8080/order/report/**.

    | Method | HTTP request | description |
    | - | - | - |
    | summary | GET summary | Get order numbers, order line numbers and subtotals. |
    | ordersOrderByStatus | GET status | Get ordered order statuses. |
    | ordersOrderByDistinctStatus | GET status/distinct | Get ordered order statuses (different sorting method).|
    | ordersByOrderNumbers | GET total/greater/`total` | Get order numbers, statuses, shipped dates and customers names for  greater than given subtotals. |
    | findByRequiredDateBetween | GET required/between/`from`/`to` | Get order numbers and statuses for required date in given range.  |
    | findByRequiredDateNotBetween | GET required/notBetween/`from`/`to` | Get order numbers and statuses for required date not in given range. |
    | getOrdersWithTotal | GET total | Get order numbers, statuses and total costs. |
    | getOrdersWithProductAndPrice | GET product/price | Get order numbers, ordered dates, order line numbers,  products names, quantities and prices. |
    | getOrdersWithCustomerAndPrice | GET customer/price | Get order numbers, ordered dates, order line numbers, prices, quantities, product names and customer names. |
    | getOrdersWithProductCode | GET orderNumber/`orderNumber` | Get customer numbers and product codes for order with given number. |

* **Product Reports**

    URIs relative to **localhost:8080/product/report/**.

    | Method | HTTP request | description |
    | - | - | - |
    | findByPriceBetween | GET price/between/`low`/`high` | Get product codes and names for products prices in given range. |
    | findByPriceNotBetween | GET price/notBetween/`low`/`high` | Get product codes and names for products prices not in given range. |
    | getProductsWithDescription | GET description | Get product names, codes and text descriptions. |
    | getProductsWithOrderNumberMsrpAndPrice | GET details/productCode/`productCode` | Get product names, MSRPs, order numbers and prices of product with given code. |

## Database Configuration

MySQL Sample Database Schema
![Database schema](https://sp.mysqltutorial.org/wp-content/uploads/2009/12/MySQL-Sample-Database-Schema.png)

### Tables

#### Customers

```sql
CREATE TABLE `customers` (
  `customerNumber` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `contactLastName` varchar(50) NOT NULL,
  `contactFirstName` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `addressLine1` varchar(50) NOT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postalCode` varchar(15) DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  `salesRepEmployeeNumber` int(11) DEFAULT NULL,
  `creditLimit` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`customerNumber`),
  KEY `salesRepEmployeeNumber` (`salesRepEmployeeNumber`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`salesRepEmployeeNumber`) REFERENCES `employees` (`employeeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Employees

```sql
CREATE TABLE `employees` (
  `employeeNumber` int(11) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `extension` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `officeCode` varchar(10) NOT NULL,
  `reportsTo` int(11) DEFAULT NULL,
  `jobTitle` varchar(50) NOT NULL,
  PRIMARY KEY (`employeeNumber`),
  KEY `reportsTo` (`reportsTo`),
  KEY `officeCode` (`officeCode`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`reportsTo`) REFERENCES `employees` (`employeeNumber`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`officeCode`) REFERENCES `offices` (`officeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Offices

 ```sql
 CREATE TABLE `offices` (
  `officeCode` varchar(10) NOT NULL,
  `city` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `addressLine1` varchar(50) NOT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  `postalCode` varchar(15) NOT NULL,
  `territory` varchar(10) NOT NULL,
  PRIMARY KEY (`officeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Order Details

```sql
CREATE TABLE `orderdetails` (
  `orderNumber` int(11) NOT NULL,
  `productCode` varchar(15) NOT NULL,
  `quantityOrdered` int(11) NOT NULL,
  `priceEach` decimal(10,2) NOT NULL,
  `orderLineNumber` smallint(6) NOT NULL,
  PRIMARY KEY (`orderNumber`,`productCode`),
  KEY `productCode` (`productCode`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Orders

```sql
CREATE TABLE `orders` (
  `orderNumber` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `requiredDate` date NOT NULL,
  `shippedDate` date DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `comments` text,
  `customerNumber` int(11) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `customerNumber` (`customerNumber`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Payments

```sql
CREATE TABLE `payments` (
  `customerNumber` int(11) NOT NULL,
  `checkNumber` varchar(50) NOT NULL,
  `paymentDate` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`customerNumber`,`checkNumber`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Productlines

```sql
CREATE TABLE `productlines` (
  `productLine` varchar(50) NOT NULL,
  `textDescription` varchar(4000) DEFAULT NULL,
  `htmlDescription` mediumtext,
  `image` mediumblob,
  PRIMARY KEY (`productLine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### Products

```sql
CREATE TABLE `products` (
  `productCode` varchar(15) NOT NULL,
  `productName` varchar(70) NOT NULL,
  `productLine` varchar(50) NOT NULL,
  `productScale` varchar(10) NOT NULL,
  `productVendor` varchar(50) NOT NULL,
  `productDescription` text NOT NULL,
  `quantityInStock` smallint(6) NOT NULL,
  `buyPrice` decimal(10,2) NOT NULL,
  `MSRP` decimal(10,2) NOT NULL,
  PRIMARY KEY (`productCode`),
  KEY `productLine` (`productLine`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`productLine`) REFERENCES `productlines` (`productLine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
