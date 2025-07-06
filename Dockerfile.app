FROM maven:3.9.10-amazoncorretto-24 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM openjdk:22-jdk

COPY --from=build /app/target/techmaps-0.0.1-SNAPSHOT.jar /app/app.jar

COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

WORKDIR /app

EXPOSE 8757

ENTRYPOINT ["./docker-entrypoint.sh"]