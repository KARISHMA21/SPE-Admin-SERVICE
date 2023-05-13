#
FROM openjdk:17
EXPOSE 8082
COPY ./target/admin_service.jar ./
WORKDIR ./
ENTRYPOINT ["java","-jar","/admin_service.jar"]
#EXPOSE 8082
#ADD target/admin_service-jar-with-dependencies.jar admin_service-jar-with-dependencies.jar
#