FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/itkAcademy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

COPY src/main/resources/db/changelog/ /app/resources/db/changelog/