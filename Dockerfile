# Usar la imagen base de Eclipse Temurin con Java 23
FROM eclipse-temurin:23-jdk

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY target/mlink-0.0.1-SNAPSHOT.jar  app.jar

# Exponer el puerto de la aplicación
EXPOSE 4202

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
