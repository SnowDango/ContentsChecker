FROM openjdk:15-slim

ENV APP_VER 0.0.1
ENV APP_DIR ~/app

RUN mkdir $APP_DIR
ADD ./source $APP_DIR
ADD gradle.properties ~/.gradle/gradle.properties
RUN cd $APP_DIR && ./gradlew distZip
RUN apt-get update && apt-get install unzip && unzip ${APP_DIR}/build/distributions/ContentsChecker-${APP_VER}.zip
ENTRYPOINT cd ${APP_DIR}/build/distributions/ContentsChecker-${APP_VER}/bin && ./ContentsChecker
