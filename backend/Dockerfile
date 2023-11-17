# Stage 1
FROM maven:3.9.5-openjdk-17 AS build

ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

WORKDIR /.

COPY . .

RUN mvn clean install -DskipTests

# Stage 2
FROM adoptopenjdk:17-jre-hotspot

WORKDIR /.

COPY --from=build target/*.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]

EXPOSE 8080