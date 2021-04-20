FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD
#FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD FOR JAVA 8

ARG SPRING_ACTIVE_PROFILE

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/

RUN mvn clean install -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE && mvn package -B -e -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE
#RUN mvn clean install -Dspring.profiles.active=dev && mvn package -B -e -Dspring.profiles.active=dev

#RUN mvn clean && mvn package 

FROM openjdk:11-slim
#FROM openjdk:8-alpine FOR JAVA 8
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/event-service-api.jar
ENTRYPOINT ["java",  "-Dspring.profiles.active=$SPRINT_ACTIVE_PROFILE", "-jar", "event-service-api.jar"]
#CMD [ "-jar", "event-service-api.jar" ]
#ENTRYPOINT [ "java" ]

#EXPOSE 8085
