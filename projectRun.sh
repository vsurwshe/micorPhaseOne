#!/bin/bash
# This is cehcking java avilable or not
echo "$(java -version)"

java -jar ./eureka/target/eureka-0.0.1-SNAPSHOT.jar &
java -jar ./zuul/target/zuil-0.0.1-SNAPSHOT.jar &
java -jar ./auth/target/auth-0.0.1-SNAPSHOT.jar &
java -jar ./profile/target/profile-0.0.1-SNAPSHOT.jar & 
java -jar ./invoice/target/invoice-0.0.1-SNAPSHOT.jar 

