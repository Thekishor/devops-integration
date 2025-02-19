FROM openjdk:21-jdk-slim
EXPOSE 9099
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]