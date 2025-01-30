# Usa una imagen de Maven con JDK 17 para la construcción
FROM eclipse-temurin:17 as build

WORKDIR /app

# Copia el código fuente
COPY . .

# Da permisos de ejecución al script mvnw
RUN chmod +x mvnw

# Ejecuta la compilación
RUN ./mvnw clean package

# Usa una imagen más liviana con solo el JRE para ejecutar la app
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia el JAR compilado desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]