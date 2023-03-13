# Worldwide-windsurfer’s-weather-service

## Assumptions:
- Expose a REST API, where the argument is a day (yyyy-mm-dd date format)
  depending on which place offers best windsurfing conditions on that day in the 16 forecast day range
  and return value is one of following locations: Jastarnia (Poland),
  Bridgetown (Barbados), Fortaleza (Brazil), Pissouri (Cyprus), Le Morne (Mauritius)
- Apart from returning the name of the location,
  the response should also include weather conditions
  (at least average temperature - Celcius, wind speed - m/s) for the location on that day.
- The best location selection criteria are:
  If the wind speed is not within <5; 18> (m/s) and the temperature is not in the range <5; 35> (°C),
  the location is not suitable for windsurfing.
- If they are in these ranges,
  then the best location is determined by the highest value calculated from the following formula:

  V * 3 + TEMP

  where V is the wind speed in m/s on a given day,
  and TEMP is an average forecasted temperature for a given day in Celsius
- If none of the locations meets the above criteria, the application does not return any.
- The list of windsurfing locations (including geographical coordinates)
  should be embedded in the application in a way that allows for extensions at a later stage.

## Requirements:
- Use Spring Boot
- Use Java 8 or higher
- Use Gradle or Maven
- Use the Weatherbit Forecast API
- Has README file

##Key points:
- Lombok - Please enable the annotation processing in your IDE
- API https://www.weatherbit.io/api/weather-forecast-16-day
- JUnit, Mockito for testing
- Gradle - build automation system
- Spring 5
- Java 16

##Run
To build project, please use:
```
gradle build
```

To run program after build:
```
java -jar build/libs/WeatherServiceApi-0.0.1-SNAPSHOT.jar
```
Or run them from the IDE.

##Test
To execute tests:
```
gradle test
```
Or run them from the IDE.

After above execution, service will start at port 8080.

## Sample requests:

Request:
```
curl -X GET \
  http://localhost:8080/weather/2022-11-20
```
Response:

```
200 OK

{
    "cityName": "Fortaleza",
    "weatherDtoList": [
        {
            "data": "2022-11-20",
            "averageTemp": 27.2,
            "windSpeed": 5.8
        }
    ]
}
```