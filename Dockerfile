FROM openjdk:21-jdk-slim

#Define la variable del archivo JAR
ARG JAR_FILE=target/Conectamente-api-0.0.1.jar

#copiar archivo JAR en el contenedor
COPY ${JAR_FILE} Conectamente-api-0.0.1.jar

#puerto en el contenedor
EXPOSE 8080

#comando para ejecutar el contenedor
ENTRYPOINT ["java", "-jar","Conectamente-api-0.0.1.jar"]
