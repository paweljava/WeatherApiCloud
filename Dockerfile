FROM openjdk:16.0.2-jdk-buster
ADD app/build/libs/app-1.0-SNAPSHOT.jar .
CMD java -jar app-1.0-SNAPSHOT.jar

