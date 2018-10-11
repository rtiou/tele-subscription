FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/tele-subscription.jar tele-subscription.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tele-subscription.jar"]