FROM openjdk:11
ADD app.jar application.properties /condominium/
CMD ["java", "-jar", "-Dspring.config.location=/condominium/application.properties", "/condominium/app.jar"]