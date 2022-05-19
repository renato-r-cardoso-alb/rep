FROM openjdk:17
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
EXPOSE 8500
LABEL org.opencontainers.image.source https://github.com/renato-r-cardoso-alb/rep