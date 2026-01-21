FROM maven:3.9.11-amazoncorretto-24@sha256:589462b6b021fcb39153a962bbabad3019f4b283c9d0ce7578152b00b6193c76 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM amazoncorretto:25.0.2@sha256:450f8c5e70f52a2fc0fa35844ace3a2bbebc90c1abe1b38460f604f90efdbda7

COPY --from=build /app/target/techmaps-0.0.1-SNAPSHOT.jar /app/app.jar

COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

WORKDIR /app

EXPOSE 8757

ENTRYPOINT ["./docker-entrypoint.sh"]
