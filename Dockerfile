#Construcción
FROM maven:3.8.5-openjdk-17 AS build

#Directorio de trabajo
WORKDIR /app

#Copiar todos los archivos
COPY . .

#Ejecutar oitiendo los test
RUN mvn clean package -DskipTests

# Ejecución
FROM openjdk:17-jdk-slim

#Directorio de trabajo
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

#Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
