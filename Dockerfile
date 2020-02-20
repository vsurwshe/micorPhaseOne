#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

COPY app7/src /home/app5/src
COPY app7/pom.xml /home/app5
RUN mvn -f /home/app5/pom.xml package

# 
# Runing The Projects
# 

FROM anapsix/alpine-java
# Builded jar files renamed and put into another library
COPY --from=build /home/app5/target/SMSAPI-0.0.1-SNAPSHOT.jar /opt/lib/SMSService.jar

# Adding outside of conatiner run.sh file into conatiner 
COPY ./projectConfig/EntryPoint.sh /EntryPoint.sh

# Execuiting the run.sh file
ENTRYPOINT ["/EntryPoint.sh"]
