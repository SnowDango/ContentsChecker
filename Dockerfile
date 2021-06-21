FROM openjdk:15-slim

ENV APP_VER 0.0.1
ENV APP_DIR /app

RUN mkdir $APP_DIR
ADD ./source /app
RUN cd /app && ./gradlew distZip
RUN apt-get update && apt-get install unzip && unzip /app/build/distributions/ContentsChecker-0.0.1.zip
ENTRYPOINT cd /app/build/distributions/ContentsChecker-0.0.1/bin && ./ContentsChecker
