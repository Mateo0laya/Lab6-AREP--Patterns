# TALLER 6 MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

El taller consiste en crear una aplicación web pequeña usando el micro-framework de Spark java (http://sparkjava.com/). Una vez tengamos esta aplicación procederemos a construir un container para docker para la aplicación y los desplegaremos y configuraremos en nuestra máquina local. Luego, cerremos un repositorio en DockerHub y subiremos la imagen al repositorio. Finalmente, crearemos una máquina virtual de en AWS, instalaremos Docker , y desplegaremos el contenedor que acabamos de crear.
Este proyecto es una aplicación web con las siguientes funciones: Seno, Coseno, Palindrome, Magnitud. El proyecto fue construido usando Java, Spark y adicionalmente se subio a DockerHub. 

## Arquitectura 
La arquitectura debe tener las siguientes características.

1. El cliente Web debe ser un cliente asíncrono que corra en el browser.
2. La aplicación debe ser multiusuario.
3. En el backend debe utilizar solo Java. No puede utilizar frameworks como SPRING.
4. Entrega archivos estáticos como páginas HTML.
5. Permite configurar el directorio de donde se leerán los archivos estáticos.
6. Permite leer parámetros del query  desde los programas.
7. SparkJava como framework para la aplicación web
8. Dockerfile y compose para el manejo de imagenes y contenedores con Docker

## Diseño de la aplicación

- La aplicación usa SparkJava para correr el servidor Http.
- Desde la clase SparkWebServer.java se puede configurar las respuestas del servidor mediante el uso de funciones Lambda.
- En el directorio \scr\main\java\com\arep\taller5\resources\public se pueen añadir archivos estaticos como HTML, CSS, JS o imagenes.

# Extensión de la aplicación

- En SparkWebServer.java se pueden añadir nuevas funciones que se desean el servidor de respuesta
- Css para mejorar la vista de la aplicación

## Guia de inicio

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Prerequisitos

- Java 8
- Maven
- Git
- Navegador web
- Docker

### Instalación

#### Github
Ubiquese en el directorio en donde desea descargar el repositorio

`git clone https://github.com/Mateo0laya/Taller-5-AREP---Docker.git`

Cambie al directorio del repositorio

`cd Taller-5-AREP---Docker`

Compile el proyecto

`mvn compile`

Empaquete el proyecto

`mvn package`

Inicie el servidor

`java -cp "target/classes:target/dependency/*" com.arep.taller5.SparkWebServer`

Una alternativa a la linea de comandos es realizar la ejecución desde un IDE. En este caso Visual Studio Code desde la clase SparkWebServer.java
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/5000a08a-7075-4c72-bf3f-3609d51e274a)

#### Docker
Recuerde tener Docker en su m[aquina para usar este medio de instalacion. Ejecute el siguiente comando:
`docker run -d -p 35000:46000 --name firstdockercontainer mateo0laya/taller5arep`

Deberá obtener una respuesta similar a la siguiente:

![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/1b5263a6-40fd-4a26-b827-0f4217c6d831)

Y verificar en DockerDesktop que esta corriendo:

![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/b10f0ab6-c271-4c2e-b8c3-f135b55db77e)



## Probando la aplicación

Si ejecuta la aplicación usando java recuerde usar el puerto 4567, en el caso de las siguientes pruebas se realizaron usando Docker por lo tanto se usa el puerto 35000:

Ingrese a la siguiente dirección: http://localhost:35000/ Deberá ver la siguiente página:
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/6a802fcc-ec35-4db9-8446-909ae9f2b0dc)

### Seno

Para la función Seno, ingrese el valor que desea calcular en **radianes** y de click en el botón de submit:
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/6f20ca0f-abb9-40a8-8213-3e601709f503)


Para la función Coseno, ingrese el valor que desea calcular en **radianes** y de click en el botón de submit:
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/cb0e85f4-ff73-4eb2-9b00-eefb5a4b76e2)

Para la función Palindromo, ingrese una palabra (**String**) y de click en el botón de submit:
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/9cf45625-15b2-44f6-a297-2756879c9126)

Para la función Magnitud, ingrese dos coordenadas **x,y** y de click en el botón de submit:
![image](https://github.com/Mateo0laya/Taller-5-AREP---Docker/assets/89365336/9b7f8433-4007-477d-9113-995b904ab13f)


## Construido con

* [Java](https://www.java.com/es/) - The main programming language
* [Maven](https://maven.apache.org/) - Dependency Management

## Version

Version 1.0.0.

## Autor

Mateo Olaya Garzon
