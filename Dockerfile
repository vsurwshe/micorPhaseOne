
# Copy the project into Image Enviorment

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Copy the parent pom xml file
COPY src /home/src
COPY pom.xml /home/

COPY auth/src /home/auth/src
COPY auth/pom.xml /home/auth
# RUN mvn -f /home/app2/pom.xml package

COPY domain/src /home/domain/src
COPY domain/pom.xml /home/domain

COPY profile/src /home/profile/src
COPY profile/pom.xml /home/profile

COPY repository/src /home/repository/src
COPY repository/pom.xml /home/repository

# Run the Parent pom.xml for building the package
 RUN mvn -f /home/pom.xml package


# 
# Runing The Projects
# 

FROM anapsix/alpine-java
# Builded jar files renamed and put into another library
# COPY --from=build /home/server/target/EurekaServer-0.0.1-SNAPSHOT.jar /opt/lib/server.jar
# COPY --from=build /home/gateway/target/zuulApiGateway-0.0.1-SNAPSHOT.jar /opt/lib/gateway.jar
COPY --from=build /home/auth/target/auth-0.0.1-SNAPSHOT.jar /opt/lib/auth.jar
COPY --from=build /home/profile/target/profile-0.0.1-SNAPSHOT.jar /opt/lib/profileAuth.jar
# COPY --from=build /home/app3/target/ResourceAPI-0.0.1-SNAPSHOT.jar /opt/lib/resource.jar
# COPY --from=build /home/app4/target/EmailAPI-0.0.1-SNAPSHOT.jar /opt/lib/emailService.jar
# COPY --from=build /home/app5/target/SMSAPI-0.0.1-SNAPSHOT.jar /opt/lib/SMSService.jar

# Adding outside of conatiner run.sh file into conatiner 
COPY ./projectConfig/EntryPoint.sh /EntryPoint.sh

# Execuiting the run.sh file
ENTRYPOINT ["/EntryPoint.sh"]

