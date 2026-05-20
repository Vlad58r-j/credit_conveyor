#!/usr/bin/env bash
set -e

cd /home/maxim/credit_conveyor

git pull

cd conveyor
./gradlew clean bootJar -x test

cd ..
docker compose up -d --build
