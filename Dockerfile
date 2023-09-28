FROM openjdk:17-alpine

WORKDIR /app

COPY target/libray-management-system-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]