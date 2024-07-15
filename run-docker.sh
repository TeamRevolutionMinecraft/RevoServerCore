#!/bin/bash

docker rm rpcore-test-container || true
docker build -t rpcore-test . && docker run --name rpcore-test-container -p 25565:25565 $@ rpcore-test