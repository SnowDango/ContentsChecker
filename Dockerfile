FROM openjdk:15-slim

ENV APP_VER 0.0.1
ENV APP_ZIP ContentsChecker-$APP_VER.zip
ENV APP_DIST ./source/build/distributions/$APP_ZIP
ENV APP_DIR /app

## main
RUN mkdir $APP_DIR

## 依存tool
RUN \
apt update && \
apt -y upgrade && \
apt install unzip

## script
ADD $APP_DIST $APP_DIR/$APP_ZIP

## 解答
RUN unzip $APP_DIR/$APP_ZIP

## SERVER起動
ENTRYPOINT cd ContentsChecker-$APP_VER/bin && ./ContentsChecker