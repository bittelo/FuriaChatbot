#!/bin/bash
echo "Setting up Java environment..."
export JAVA_HOME=/opt/java/openjdk
export PATH=$PATH:$JAVA_HOME/bin
echo "Java version:"
java -version
echo "Running Maven build..."
chmod +x ./mvnw
./mvnw clean package