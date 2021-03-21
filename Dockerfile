FROM adoptopenjdk/openjdk11:alpine
RUN apk --no-cache add curl

VOLUME /tmp

ARG REGION_ARG=sa-east-1
ARG ACCESS_KEY
ARG AWS_SECRETKEY
ENV AWS_REGION=$REGION_ARG
ENV AWS_ACCESS_KEY=$ACCESS_KEY
ENV AWS_SECRET_KEY=$AWS_SECRETKEY
ARG JAR_FILE=app/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
