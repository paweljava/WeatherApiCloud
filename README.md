# Worldwide-windsurfer’s-weather-service

## Assumptions:
- Expose a REST API, where the argument is a day (yyyy-mm-dd date format)
  depending on which place offers best windsurfing conditions on that day in the 16 forecast day range
  and return value is list of following locations: Jastarnia (Poland),
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
- Use H2 database
- Use the Visual Crossing Forecast API
- Has README file

##Key points:
- Lombok - Please enable the annotation processing in your IDE
- API https://www.visualcrossing.com/weather-api
- JUnit, Mockito, WireMock for testing
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
java -jar app/build/libs/app-1.0-SNAPSHOT.jar
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

Example request:
```
curl -X GET \
  http://localhost:8080/weather/2023-05-29
```
Example response:

```
200 OK

[
    {
        "cityName": "Le Morne",
        "weatherDtoList": [
            {
                "datetime": "2023-05-29",
                "temp": 24.6,
                "windspeed": 28.8
            }
        ]
    },
    {
        "cityName": "Fortaleza",
        "weatherDtoList": [
            {
                "datetime": "2023-05-29",
                "temp": 26.9,
                "windspeed": 23.8
            }
        ]
    },
    {
        "cityName": "Bridgetown",
        "weatherDtoList": [
            {
                "datetime": "2023-05-29",
                "temp": 27.2,
                "windspeed": 22.7
            }
        ]
    },
    {
        "cityName": "Pissoúri",
        "weatherDtoList": [
            {
                "datetime": "2023-05-29",
                "temp": 21.2,
                "windspeed": 21.6
            }
        ]
    },
    {
        "cityName": "Jastarnia",
        "weatherDtoList": [
            {
                "datetime": "2023-05-29",
                "temp": 12.2,
                "windspeed": 24.1
            }
        ]
    }
]
```
## Database H2 access

H2 console available at '/console'
Example access to console:
```
localy: http://localhost:8080/console
remote: http://WebPageName/console
```
```
JDBC URL: 'jdbc:h2:mem:test'
User Name: sa
Password: No password / empty field
```
