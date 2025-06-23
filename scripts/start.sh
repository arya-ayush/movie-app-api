#!/bin/bash
cd /home/ubuntu/app
sudo pkill -f 'java -jar' || true
nohup java -jar app.jar > app.log 2>&1 &