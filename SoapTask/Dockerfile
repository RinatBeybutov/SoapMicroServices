FROM openjdk:8
ADD target/producer-soap.jar producer-soap.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "producer-soap.jar"]