FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:application-prod.properties"]