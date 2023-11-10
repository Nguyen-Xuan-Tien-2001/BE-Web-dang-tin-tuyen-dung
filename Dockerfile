#base image linux alphe os with open jdk 17
FROM openjdk:17
#copy target from local into docker image
COPY target/chatapp-0.0.1-SNAPSHOT.jar chatapp-0.0.1-SNAPSHOT.jar
#command line to run jar
ENTRYPOINT ["java", "-jar", "/chatapp-0.0.1-SNAPSHOT.jar"]