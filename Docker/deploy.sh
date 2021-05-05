#!/bin/bash

cd ..
mvn clean package
cp target/condominium-api-0.1.jar Docker/app.jar
cd Docker
sudo docker-compose up -d
