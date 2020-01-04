#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Copy first project and packageing
COPY token-1/src /home/app1/src
COPY token-1/pom.xml /home/app1
RUN mvn -f /home/app1/pom.xml package

# Copy second project and packageing
COPY token-2/src /home/app2/src
COPY token-2/pom.xml /home/app2
RUN mvn -f /home/app2/pom.xml package

# Copy thired project and packageing
COPY token-3/src /home/app3/src
COPY token-3/pom.xml /home/app3
RUN mvn -f /home/app3/pom.xml package

# 
# Runing The Projects
# 

FROM anapsix/alpine-java
# Builded jar files renamed and put into another library
COPY --from=build /home/app1/target/token-0.0.1-SNAPSHOT.jar /opt/lib/demo1.jar
COPY --from=build /home/app2/target/token-0.0.1-SNAPSHOT.jar /opt/lib/demo2.jar
COPY --from=build /home/app3/target/token-0.0.1-SNAPSHOT.jar /opt/lib/demo3.jar

# Adding outside of conatiner run.sh file into conatiner 
ADD run.sh /run.sh

# Execuiting the run.sh file
ENTRYPOINT ["/run.sh"]
