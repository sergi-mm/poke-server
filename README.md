# Poké Server

Simple test project to manage Pokemons data.

Data retrieved from https://pokeapi.co/api/v2/

## Project dependencies
These are the project required dependencies:

 - Java 8
 - Maven
 - SpringBoot with Spring Data and Spring MWC
 - Lombok
 - Swagger
 - H2 database (File storage)

## How to run

Simply execute the `PokeServerApplication` as a Java application to automatically start the Spring Boot Server. At start, the application will automatically populate the Pokemons belonging to "Red" version from PokeAPI database. Database is in H2 file, so is non volatile, and in following start, if data is present it will not retrieve all data again.

## HTTP Endpoints

The following endpoints have been added:

 - `/` : returns the server status
 - `/pokemons/count` : returns the number of Pokemons present in database - 
 - `/pokemons/all` : returns *all* Pokemons present in database
 - `/pokemons/top-height` : returns the top Pokemons ordered by higher height. By default returns 5 Pokemons, but with the parameter `max-results`, you can change the number of pokemons returned.
 -  `/pokemons/top-weight` : returns the top Pokemons ordered by higher weight. By default returns 5 Pokemons, but with the parameter `max-results`, you can change the number of pokemons returned.
 - `/pokemons/top-base-experience` : returns the top Pokemons ordered by higher base experience. By default returns 5 Pokemons, but with the parameter `max-results`, you can change the number of pokemons returned. 
 - `/import-data` : endpoint to manually reimport all Pokemons data into database. Returns the number of Pokemons imported.
 
## Tests

Junit tests classes 
- `PokemonControllerTests` includes tests for public HTTP Endpoints 
- `PokemonRestServiceTest` includes tests for internal service which fetchs Pokemons from PokeAPI.