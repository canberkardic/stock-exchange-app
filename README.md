# Stock Exchange Application

Stock Exchange MVP project based on Spring Boot & Maven.

This application is an MVP that relies on transactional operation between Stocks and StockExchanges.
There is many stocks and the many stock exchanges in the world and this application basicly shows their relation using;

- Spring Boot
- Spring Data JPA 
- Spring Security
- H2 Database
- Lombok
- Swagger
- SL4J
- Docker

## Project Structure

Project architecture is based on the layered / n-tier archictecture.
You can find the Components regarding; Controller - Service - Repository and DTO.

Other than the layered archictecture, the project includes;
 - Custom Exceptions
 - CustomAuthenticationEntryPoint for Filter Enhancing
 - Data Initialization on startup via SQL script

The data is stored in the `STOCK`,`STOCK_EXCHANGE`,`STOCK_EXCHANGE_STOCK` tables in an H2 database.<br> 
During the start-up process, dummy datas are populated to related tables  by `data.sql` file.<br>

There are 2 

## Stock Controller

The `StockController` handles the operations related to the stocks.  <br>
It's communicating with the `StockServiceImpl` to perform business logic operations and handles HTTP requests and responses for the following endpoints:

- `POST /api/v1/stock`: Creates a new stock.
- `DELETE /api/v1/stock/{id}`: Deletes a stock by id
- `PUT /api/v1/stock/`: Updates the price of a stock.


## Stock Exchange Controller

The `StockExchangeController` class manages operations related to stock exchanges in the system.<br>
It's communicating with the `StockExchangeServiceImpl` to perform business logic operations and handles HTTP requests and responses for the following endpoints:

- `GET /api/v1/stock-exchange/{name}`: Retrieves all stocks associated with a specific stock exchange.
- `DELETE /api/v1/stock-exchange/{name}?stockName={stockName}`: Deletes a stock from a specific stock exchange.
- `POST /api/v1/stock-exchange/{name}?stockName={stockName}`: Adds a stock to a specific stock exchange.


## Getting started & Running the application

- Clone the project using github.
- Make sure you have docker & docker-compose installed in your computer
- Run your docker agent
- Run below command

```bash
docker-compose up --build
```
The application will be running on the port `6070`


## API Documentation

You can access the Swagger API documentation and usage at below URL

```
http://localhost:6070/swagger-ui/index.html
```

## H2 Database

The username and password can be found in the `application.properties` file.<br>
You can log in to the H2 database console at the following URL:
```
http://localhost:6070/h2-console/login.do
```

## Initial Data Details for Testing

There are 2 users exists in the applicaiton and their credentials are already placed in the application.properties

If you want to run the project with other username & password combinations, you can change them from the 
application.properties.
Apart from the users, the initial data is **populated everytime when the application runs**.
The SQL script can be found in the /resources/data.sql
