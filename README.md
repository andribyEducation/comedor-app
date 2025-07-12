# comedor-app

Este proyecto es una aplicación de escritorio desarrollada en Java con Maven.

## Estructura del Proyecto

La estructura de directorios de este proyecto sigue las convenciones de Maven para proyectos Java, con algunas adiciones para la organización de componentes de UI y recursos.

-   `pom.xml`: El archivo de configuración principal de Maven para el proyecto. Define dependencias, plugins y la forma en que se construye el proyecto.
-   `src/`: Contiene el código fuente del proyecto.
    -   `main/`: Código fuente principal de la aplicación.
        -   `java/`: Archivos fuente Java.
            -   `com/ucv/comedor/`: Paquete principal de la aplicación.
                -   `App.java`: La clase principal que inicia la aplicación.
            -   `components/`: Contiene componentes de UI reutilizables.
                -   `Button/`: Componentes de botón personalizados.
                    -   `RoundedButton.java`: Un botón con esquinas redondeadas.
            -   `listeners/`: Clases que manejan eventos de UI.
                -   `StyledButtonMouseListener.java`: Listener para efectos de hover y click en botones.
            -   `views/`: Contiene las diferentes vistas o pantallas de la aplicación.
                -   `home/`: Vista principal de la aplicación.
                    -   `Home.java`: La ventana principal de la aplicación.
    -   `test/`: Código fuente para las pruebas unitarias.
        -   `java/`: Archivos fuente Java para pruebas.
            -   `com/ucv/comedor/`: Paquete de pruebas.
                -   `AppTest.java`: Clase de prueba para la aplicación principal.
-   `fonts/`: Contiene los archivos de fuentes personalizados utilizados en la aplicación.
-   `target/`: Directorio generado por Maven que contiene los archivos compilados, paquetes (JARs) y resultados de pruebas.
-   `LICENSE`: Archivo de licencia del proyecto.
-   `.git/`: Directorio de control de versiones de Git.

## Cómo Compilar y Ejecutar

Para compilar el proyecto, navega a la raíz del proyecto en tu terminal y ejecuta:

```bash
mvn clean install
```

Para ejecutar la aplicación después de compilar, puedes usar el plugin `exec-maven-plugin` configurado en `pom.xml`:

```bash
mvn compile exec:java
```