# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="athul.ravindran87@gmail.com"

# Add a volume pointing to /tmp
VOLUME /usr/app

COPY /target/client-service-1-0.1-SNAPSHOT.jar /tmp/client-service-1.jar

WORKDIR /tmp

EXPOSE 8763

# Run the jar file
ENTRYPOINT ["java","-jar","-Xms1024m", "-Xmx2048m","hazelcast-client.jar"]