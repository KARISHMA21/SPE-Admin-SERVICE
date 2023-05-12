FROM openjdk:18
ADD target/admin_service-0.0.1-SNAPSHOT.jar admin_service-docker.jar
ENTRYPOINT ["java","-jar","/admin_service-docker.jar"]

