FROM openjdk:17-oracle
WORKDIR .
COPY build/libs/h2o-hydroponics2operate-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]