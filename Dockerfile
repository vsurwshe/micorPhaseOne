#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Copy first project and packageing
COPY auth/src /home/app1/src
COPY auth/pom.xml /home/app1
RUN mvn -f /home/app1/pom.xml package

# Copy second project and packageing
COPY resource/src /home/app2/src
COPY resource/pom.xml /home/app2
RUN mvn -f /home/app2/pom.xml package

# 
# Runing The Projects
# 

FROM anapsix/alpine-java
# Builded jar files renamed and put into another library
COPY --from=build /home/app1/target/token-0.0.1-SNAPSHOT.jar /opt/lib/auth.jar
COPY --from=build /home/app2/target/token-0.0.1-SNAPSHOT.jar /opt/lib/resource.jar

# Adding outside of conatiner run.sh file into conatiner 
ADD run.sh /run.sh

# Execuiting the run.sh file
ENTRYPOINT ["/run.sh"]
