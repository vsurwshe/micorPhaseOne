# Copy the project into Image Enviorment
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Copy the parent pom xml file
COPY src /home/src
COPY pom.xml /home/

# -------- Address
COPY address/src /home/address/src
COPY address/pom.xml /home/address

# -------- Auth
COPY auth/src /home/auth/src
COPY auth/pom.xml /home/auth
# RUN mvn -f /home/app2/pom.xml package

# -------- Customer
COPY customer/src /home/customer/src
COPY customer/pom.xml /home/customer

# -------- Domain
COPY domain/src /home/domain/src
COPY domain/pom.xml /home/domain

# ------- Eureka
COPY eureka/src /home/eureka/src
COPY eureka/pom.xml /home/eureka

# ------- Exception
COPY exception/src /home/exception/src
COPY exception/pom.xml /home/exception

# ------- Food
COPY food/src /home/food/src
COPY food/pom.xml /home/food

# ------- Food Invoice
COPY foodInvoice/src /home/foodInvoice/src
COPY foodInvoice/pom.xml /home/foodInvoice

# ------- Hotel Table
COPY hotel-tabel/src /home/hotel-tabel/src
COPY hotel-tabel/pom.xml /home/hotel-tabel

# ------- Customer Invoice
COPY invoice/src /home/invoice/src
COPY invoice/pom.xml /home/invoice

# ------- Payment
COPY payment/src /home/payment/src
COPY payment/pom.xml /home/payment

# ------- Profile
COPY profile/src /home/profile/src
COPY profile/pom.xml /home/profile

# ------- Repository
COPY repository/src /home/repository/src
COPY repository/pom.xml /home/repository

# ------- Service
COPY service/src /home/service/src
COPY service/pom.xml /home/service

# -------- Zuul Api Gateway
COPY zuul/src /home/zuul/src
COPY zuul/pom.xml /home/zuul

# Run the Parent pom.xml for building the package
 RUN mvn -f /home/pom.xml package

# 
# Runing The Projects
# 

FROM anapsix/alpine-java
# Builded jar files renamed and put into another library
COPY --from=build /home/eureka/target/eureka-0.0.1-SNAPSHOT.jar /opt/lib/server.jar
COPY --from=build /home/zuul/target/zuil-0.0.1-SNAPSHOT.jar /opt/lib/gateway.jar
COPY --from=build /home/auth/target/auth-0.0.1-SNAPSHOT.jar /opt/lib/auth.jar
COPY --from=build /home/profile/target/profile-0.0.1-SNAPSHOT.jar /opt/lib/profileAuth.jar
COPY --from=build /home/invoice/target/invoice-2.1.9.RELEASE.jar /opt/lib/customerInvoice.jar
# COPY --from=build /home/app3/target/ResourceAPI-0.0.1-SNAPSHOT.jar /opt/lib/resource.jar
# COPY --from=build /home/app4/target/EmailAPI-0.0.1-SNAPSHOT.jar /opt/lib/emailService.jar
# COPY --from=build /home/app5/target/SMSAPI-0.0.1-SNAPSHOT.jar /opt/lib/SMSService.jar

# Adding outside of conatiner run.sh file into conatiner 
COPY ./projectConfig/EntryPoint.sh /EntryPoint.sh

# Execuiting the run.sh file
ENTRYPOINT ["/EntryPoint.sh"]

