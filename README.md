# Temperature REST API

Simple temperature REST API where you can get current temperature for the given city.
You can also store your favorite city and get the temperature by that favorite city id.

## Getting Started

To get this project running on your local machine, you'll need to git clone it,
them import it to your chosen IDE. You'll also need to have an OpenWeatherMap API key,
which you can then add as an environmental variable. The env variable is named 
"OPENWEATHERMAP_API_KEY" as can be seen in application.properties file. After that
you can try and run the project. In the development phase H2 in-memory database is used.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


#### TODO

##### Add:

* unit tests
* integration tests
* error handling in corner cases
* more Swagger documentation

##### Fix:

* crash when using non-number id

##### Learn:

* to name things properly...
* Swagger
* more about testing