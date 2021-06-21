#!/bin/bash

cd source
./gradlew distZip

cd ..
docker build -t contents-checker .
docker run -m1G --cpus 2 -it -p 8080:8080 --rm contents-checker
