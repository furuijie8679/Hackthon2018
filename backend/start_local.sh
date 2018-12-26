#!/usr/bin/env bash
./gradlew clean
./gradlew build

echo "start service ..."
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar