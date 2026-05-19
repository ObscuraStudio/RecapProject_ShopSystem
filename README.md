# Recap_Project — Backend Shop System

[![Java CI](https://github.com/ObscuraStudio/RecapProject_ShopSystem/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/ObscuraStudio/RecapProject_ShopSystem/actions)

A Shop Service System, which controls and tracks all products & orders.

## Build, Run & Tests

**Requirements:** Java 25+, Maven 3.8+

**Run tests locally:** `mvn clean verify`

**Build artifact:** `mvn package`

Produces a JAR in `target/*.jar`.

## Project Structure

src/main/java/→ Main.java (shop logic)

src/test/java/→ MainTest.java (JUnit 5 tests)

## Usage

After building, run from the project root:

​```
java -jar target/RecapProject_ShopSystem-1.0-SNAPSHOT.jar
​```

## CI

GitHub Actions runs `mvn clean verify` on every push and pull request
to `master`. See the badge above for current build status.

## Bonus Scope

- Price and quantity for products :white_check_mark:
- Tests for all classes :white_check_mark:
- Input reader for CLI usage :white_check_mark:
- Color and format adjusted for outputs :white_check_mark:
- EAN database for input (CSV format) :white_check_mark:
- added order status via Enums :white_check_mark:
- returning empty Optionals instead of null :white_check_mark:
- Exception handling, if product does not exist :white_check_mark:
- Order class from record to Lombok support :white_check_mark:
- added updateOrder method :white_check_mark:
- implemented UUID generation :white_check_mark:
- timestamps added at order creation and sorting by oldest order :white_check_mark:
- advanced product information (tracking of stock, error when not enough stock)
- control of stock for receiving and sending
- protocol for every action in the warehouse
