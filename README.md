# Proyecto Ticketera "Soporte Unla"

Proyecto realizado para la materia **Orientación a Objetos 2**

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado y configurado lo siguiente:

- **Java JDK 21**  
  Puedes descargarlo desde: [aqui](https://www.oracle.com/ar/java/technologies/downloads/#java21)

- **Maven** (para compilar y correr el proyecto)  
  [Maven](https://maven.apache.org/install.html)

- **SQL Server** (o el gestor de base de datos que uses)  
  Debes tenerlo instalado y funcionando.

- **Plugin Lombok** en tu IDE

## 🛠️ Clonar y configurar el proyecto

1. **Cloná el repositorio:**

```bash
git clone https://github.com/JulyS2001/TP-Oo2-Spring-Grupo4.git
cd TP-Oo2-Spring-Grupo4
```
## Importar el proyecto en tu IDE
En este caso fue realizado con el IDE Spring Tool Suite.

## Configuración de la base de datos

1. Descarga el script SQL para crear la base de datos y las tablas desde este enlace:  
   [Script SQL](https://drive.google.com/file/d/1swvsM6oDdQlNV0rw5CZutqBWOQunID0N/view?usp=drive_link)

2. Ejecuta el script en tu servidor SQL Server para crear la base de datos y las tablas necesarias.


## 🛠️ Modificar las variables de entorno
Ubicación del archivo: [src/main/resources/application.yml](src/main/resources/application.yml)

```yaml

spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}

  jpa:
    show-sql: true

  mail:
    host: ${MAIL_HOST}
    port: ${MAIL_PORT}
    username: ${MAIL_USER}
    password: ${MAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```
## Luego de compilar el proyecto accede a la aplicación

```yaml
Abrí tu navegador en:
http://localhost:8080
```

## Credenciales de los perfiles ya guardados en la base de datos:

```yaml
mail: admin@gmail.com contraseña: admin123
mail: anahi@gmail.com contraseña: anahi123
mail: axel@gmail.com contraseña: axel123
mail: julian@gmail.com contraseña: julian123
mail: marcos@gmail.com contraseña: marcos123
```
