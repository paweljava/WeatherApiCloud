FROM openjdk:16.0.2-jdk-buster
ADD app/build/libs/app-1.0-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar app-1.0-SNAPSHOT.jar

