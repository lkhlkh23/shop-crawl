#!/bin/bash

REPOSITORY=/home/ec2-user/app/shop-crawl
APP_DIRECTORY=/home/ec2-user/app

if [ ! -d "REPOSITORY" ]; then
    echo "> Create Directory"
    mkdir /home/ec2-user/app

    cd $REPOSITORY
    pwd

    echo "> Git Clone"
    git clone https://github.com/lkhlkh23/shop-crawl.git
else
    echo "> Already Exist Directory"

    cd $REPOSITORY
    pwd
fi

cd shop-crawl

echo "> Git Pull"
git pull

echo "> Start Build"
./gradlew build -x test

echo "> Confirm Current Pid"
CURRENT_PID=$(lsof -t -i:8080)
echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> Not Exist Current Pid"
    echo "> Not Kill Pid"
else
    echo "> kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> Deploy"
java -jar build/libs/shop-0.0.1-SNAPSHOT.jar