FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/workerPatternRedis-1.jar workerpatternredis.jar
ENTRYPOINT ["java","-jar","/workerpatternredis.jar"]