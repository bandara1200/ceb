FROM openjdk:8
EXPOSE 8088
ADD target/ceb-app.jar ceb-app.jar
ENTRYPOINT ["java", "-jar", "/ceb-app.jar"]
