FROM maven:3.9.11-amazoncorretto-24@sha256:589462b6b021fcb39153a962bbabad3019f4b283c9d0ce7578152b00b6193c76 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM amazoncorretto:25.0.3@sha256:155382d63875f030451fc904828bcb585103cec0e9dc52e210517f76255a1111

COPY --from=build /app/target/techmaps-0.0.1-SNAPSHOT.jar /app/app.jar

COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

WORKDIR /app

EXPOSE 8757

ENTRYPOINT ["./docker-entrypoint.sh"]
