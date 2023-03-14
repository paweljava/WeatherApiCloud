FROM openjdk:17
ADD app/build/libs/app-1.0-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar app-1.0-SNAPSHOT.jar

