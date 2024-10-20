# Aplicación Pico y Placa
Es una aplicación desarrollada con Spring Boot y Angular en su version 18. Realiza la validación de la restricción de circulación vehicular "Pico y Placa" de la ciudad de Quito.
Esta aplicación determina si un vehículo puede circular en función de su número de placa y la hora y día especificados.

![picoplaca](https://github.com/user-attachments/assets/02f90938-6a23-4470-a6eb-ef3678f9f2b2)


- Link de la app desplegada: https://tourmaline-cupcake-6e4e29.netlify.app/
  (Al ser un servicio gratuito tarde 60 segundos o más en dar la primera respuesta)
- Link de la parte fronted: https://github.com/WashingtonLlumitaxi/pico-placa-web

  # Backed de la aplicación
## Ejecución del proyecto
- Backend desarrollado con Spring Boot
- Versiones

Spring Boot:  3.3.4

Java: 17

## Instalación 

- Clonar el repositorio
  
  git clone https://github.com/WashingtonLlumitaxi/pico-placa-api.git
  
- Entrar al directorio del proyecto
  
  cd pico-placa-api
  
- Construir el proyecto
  
  mvn clean package -DskipTests

- Ejecutar la aplicación
  
  java -jar target/picoplacaapi.jar

## Uso
- Endpoint
  
POST: http://localhost:8080/api/v1/placas/validar

- Ejemplo de solicitud
{
   "placa": "ABC1238",
    "fechaHora": "2024-10-17T06:40:00"
}

## Ejecución del archivo Dockerfile

- 1 Construir la imagen

  docker build -t picoplacaapi .

- 2 Ejecutar el contenedor

  docker run -d -p 8080:8080 picoplacaapi

   
# Despligue de la aplicación

La aplicación se halla desplegada con Render.
- Pasos para el despliegue

- 1 Dockerizar la aplicación con el archivo Dockerfile del proyecto
- 2 Publicar la aplicación de forma pública a Docker Hub
- 3 Utilizar el Web Service de Render para publicar la imagen de docker hub
- 4 Realizar el deploy


# Fronted de la aplicación

- Link: https://github.com/WashingtonLlumitaxi/pico-placa-web
 
## Ejecución del proyecto
- Fronted desarrollado con Angular 18
- Versiones

Angular CLI: 18.1.4

Node: 20.17.0

Package Manager: npm 10.8.2

## Instalación 

- Clona el repositorio
  
  git clone https://github.com/WashingtonLlumitaxi/pico-placa-web.git
  
- Entra en el directorio del proyecto
  
  cd pico-placa-web
  
- Instalación de dependencias
  
  np install

- Ejecuta la aplicación
  
  ng serve

## Uso
- Endpoint
  
http://localhost:4200/

   
# Despligue de la aplicación

La aplicación se halla desplegada con Netlify.
- Pasos para el despliegue

- 1 Crear el archivo dist en el proyecto con el siguiente comando npm run build
- 2 Subir el proyecto en Github
- 2 Compartir el proyecto de Github con Netlify
- 3 Realiza el deploy

    
