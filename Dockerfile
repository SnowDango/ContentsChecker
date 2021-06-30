FROM openjdk:15-slim

ENV APP_SOURCE source/
ENV APP_DIR /app

## main
RUN mkdir $APP_DIR

## 依存tool
RUN \
apt update && \
apt -y upgrade && \
apt install unzip

## script
ADD $APP_SOURCE $APP_DIR/

## SERVER起動
ENTRYPOINT cd ./source/ && ./gradlew run