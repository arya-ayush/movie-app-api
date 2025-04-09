#!/bin/bash

# Set vars
JAR_NAME=movie-app-0.0.1-SNAPSHOT.jar
JAR_PATH=/Users/divyanshu/Documents/API/movie-app-api/target/$JAR_NAME
KEY_PATH="/Users/divyanshu/Documents/FullMoviesLambda/ssh/fullmovies.pem"
USER=ubuntu
HOST=ec2-43-204-108-0.ap-south-1.compute.amazonaws.com
REMOTE_DIR=/home/ubuntu

# Build project
echo "Building JAR..."
 ./mvnw clean package

# Upload to server
echo "Uploading JAR to server..."
scp -i "$KEY_PATH" "$JAR_PATH" $USER@$HOST:$REMOTE_DIR

# Restart app using nohup
echo "Restarting app on server..."
ssh -i "$KEY_PATH" $USER@$HOST << EOF
    pkill -f $JAR_NAME || true
    nohup java -jar $REMOTE_DIR/$JAR_NAME > logs/movie-app.log 2>&1 &
    echo "App restarted with nohup"
EOF