#!/usr/bin/env bash

#
# This script builds the image based on ./Dockerfile. The image
# is the executed with the ./run.sh script.
#

echo "Reading environment variables..."
source ./env.sh

echo "Reading application secrets..."
source ../.env

echo "Removing old container..."
docker rm -f $CONTAINER_NAME

echo "Removing old image(s)..."
docker rmi -f $CONTAINER_IMAGE

echo "Copying latest webapp..."
cp ../target/weatherinfo.war ./ROOT.war

echo "Building docker image..."
docker build \
    --build-arg IPINFO_SERVICE_TOKEN=$IPINFO_SERVICE_TOKEN \
    --build-arg WUNDERGROUND_API_KEY=$WUNDERGROUND_API_KEY \
    -t $CONTAINER_IMAGE .