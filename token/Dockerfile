#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM anapsix/alpine-java
COPY --from=build /home/app/target/token-0.0.1-SNAPSHOT.jar /opt/lib/demo.jar
EXPOSE 7001
ENTRYPOINT ["java","-jar", "/opt/lib/demo.jar"]
