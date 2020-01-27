#!/bin/bash
# This is cehcking java avilable or not
echo "$(java -version)"

# This line executing second jar file and puting as background process using opertaor( & )
java -jar /opt/lib/server.jar &
# This line executing second jar file and puting as background process using opertaor( & )
java -jar /opt/lib/auth.jar &
# This line executing second jar file and puting as background process using opertaor( & )
java -jar /opt/lib/profileAuth.jar &
# This line continoues executing jar file not puting as background process
java -jar /opt/lib/resource.jar 






