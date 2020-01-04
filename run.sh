#!/bin/bash
# This is cehcking java avilable or not
echo "$(java -version)"

# This line executing first jar file and puting as background process using opertaor( & )
java -jar /opt/lib/demo1.jar &
# This line executing second jar file and puting as background process using opertaor( & )
java -jar /opt/lib/demo2.jar &
# This line continoues executing jar file not puting as background process
java -jar /opt/lib/demo3.jar 






