# Backend Ecommerce

Este proyecto es una aplicación de comercio electrónico desarrollada con Spring Boot 5 para el backend, Angular para el frontend y PostgreSQL como base de datos.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Instrucciones de Instalación](#instrucciones-de-instalación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Licencia](#licencia)

## Características

- Registro e inicio de sesión de usuarios
- Gestión de productos (CRUD)
- Carrito de compras
- Procesamiento de pedidos
- Gestión de usuarios y roles
- Integración con pasarelas de pago

## Tecnologías

- **Backend**: Spring Boot 5
- **Frontend**: Angular
- **Base de Datos**: PostgreSQL
- **Seguridad**: Spring Security
- **ORM**: Hibernate
- **Construcción del Proyecto**: Maven para el backend, Angular CLI para el frontend

## Requisitos Previos

- Java 17 o superior
- Node.js y npm
- Angular CLI
- PostgreSQL
- Maven

## Configuración del Entorno

### Backend

1. **Clonar el repositorio**:
    ```sh
    git clone https://github.com/tu-usuario/ecommerce-spring-angular.git
    cd ecommerce-spring-angular/backend
    ```

2. **Configurar la base de datos**:
    - Crear una base de datos en PostgreSQL.
    - Actualizar el archivo `application.properties` con las credenciales de tu base de datos.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

3. **Construir y ejecutar el proyecto**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

### Frontend

1. **Navegar al directorio del frontend**:
    ```sh
    cd ../frontend
    ```

2. **Instalar las dependencias**:
    ```sh
    npm install
    ```

3. **Ejecutar la aplicación Angular**:
    ```sh
    ng serve
    ```

## Instrucciones de Instalación

### Backend

1. Asegúrate de tener PostgreSQL instalado y en funcionamiento.
2. Configura la base de datos según lo descrito en la sección de [Configuración del Entorno](#configuración-del-entorno).
3. Ejecuta los comandos Maven para construir y ejecutar el backend.

### Frontend

1. Asegúrate de tener Node.js y Angular CLI instalados.
2. Navega al directorio del frontend y ejecuta los comandos npm para instalar dependencias y ejecutar el servidor de desarrollo de Angular.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más información.
