FROM openjdk:8

EXPOSE 8080

RUN mkdir -p /opt/application/jar
WORKDIR /opt/application/jar
COPY build/libs/vote-ms-0.0.1-SNAPSHOT.jar /opt/application/jar/vote-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", \ 
    "-jar", \ 
    "/opt/application/jar/vote-ms-0.0.1-SNAPSHOT.jar" ]