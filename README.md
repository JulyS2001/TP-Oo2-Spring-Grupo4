# Proyecto Ticketera "Soporte Unla"

Proyecto realizado para la materia **Orientación a Objetos 2**

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado y configurado lo siguiente:

- **Java JDK 21**  
  Puedes descargarlo desde: https://www.oracle.com/ar/java/technologies/downloads/#java21

- **Maven** (para compilar y correr el proyecto)  
  https://maven.apache.org/install.html

- **SQL Server** (o el gestor de base de datos que uses)  
  Debes tenerlo instalado y funcionando.

- **Plugin Lombok** en tu IDE (En este caso se utilizó Spring Tool Suite)

## Configuración de la base de datos

1. Descarga el script SQL para crear la base de datos y las tablas desde este enlace:  
   [Script SQL](https://drive.google.com/file/d/10oYaoCrtMw4JJfOxrbc5AggqWKmE421_/view?usp=drive_link)

2. Ejecuta el script en tu servidor SQL Server para crear la base de datos y las tablas necesarias.

## 🛠️ Clonar y configurar el proyecto

1. **Cloná el repositorio:**

```bash
git clone https://github.com/JulyS2001/TP-Oo2-Spring-Grupo4.git
cd TP-Oo2-Spring-Grupo4
```

## Modificá las credenciales en el archivo application.yml

Ubicación: src/main/resources/application.yml

yaml
Copiar
Editar
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bd-ticketera
    username: TU_USUARIO
    password: TU_CONTRASEÑA
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      dialect: org.hibernate.dialect.MySQL8Dialect
  thymeleaf:
    cache: false
