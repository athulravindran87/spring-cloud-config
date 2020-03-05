# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="athul.ravindran87@gmail.com"

# Add a volume pointing to /tmp
VOLUME /usr/app

COPY /target/client-discovery-first-0.1-SNAPSHOT.jar /tmp/client-discovery-first.jar

WORKDIR /tmp

EXPOSE 8764

# Run the jar file
ENTRYPOINT ["java","-jar","-Xms1024m", "-Xmx2048m","client-discovery-first.jar"]