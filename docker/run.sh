#!/usr/bin/env bash

# The location of this script. The place where the data directory
# `basicdata` is located.
export RUN_SCRIPT_DIRECTORY="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P )"

echo "Reading common environment variables..."
source ./env.sh

echo "Reading application secrets..."
source ../.env

echo "Removing old container..."
docker rm -f $CONTAINER_NAME

# docker run
# -p           forwards TCP traffic from localhost's port $HOST_PORT to the
#              port $CONTAINER_PORT inside the container
# --name       gives the container the name that shows up with `docker ps -a`
# --volume     maps $HOST_DATA_DIRECTORY to the container so it shows 
#              up as $CONTAINER_DATA_DIRECTORY
#
# $CONTAINER_IMAGE is the image used to build the container, 
#             `docker images` lists all images on this host.

echo "Starting new container..."
docker run \
  -p $HOST_PORT:$CONTAINER_PORT \
  --name $CONTAINER_NAME \
  --volume $RUN_SCRIPT_DIRECTORY/basicdata:/data \
  $CONTAINER_IMAGE
