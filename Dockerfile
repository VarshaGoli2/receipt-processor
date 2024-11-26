#My java version -21
FROM openjdk:21-jdk-slim 

WORKDIR /app

COPY target/receipt-processor-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar" ]