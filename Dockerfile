FROM openjdk:17
COPY "./target/cine-gestion-1.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]