FROM openjdk:21-jdk-slim

#Define la variable del archivo JAR
ARG JAR_FILE=target/archivo_jar

#copiar archivo JAR en el contenedor
COPY ${JAR_FILE} archivo_jar

#puerto en el contenedor
EXPOSE 8080

#comando para ejecutar el contenedor
ENTRYPOINT ["java", "-jar","achivo_rar"]
