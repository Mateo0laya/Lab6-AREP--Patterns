# TALLER 6 MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

Para este taller se debe crear una aplicación web, la cual esta dividida en una fachada que recibe las peticiones del cliente web y las redirige a un balanceador de carga que implementa el algoritmo Round Robin para redirigir nuevamente las solicitudes a uno de los tres servicios encargados de atender cada una de las peticiones, por último, estos servicios tienen conexión a una base de datos Mongo donde deberá almacenar y solicitar los datos almacenados en la base de datos.
Cada uno de estos componentes debe ser una imagen propia corriendo en el motor de docker, dentro de un EC-2 de AWS después de haber configurado correctamente las reglas de seguridad de entrada apropiadas para el correcto funcionamiento de la aplicación.

## Arquitectura 
La arquitectura debe tener las siguientes características.

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/d5495f99-84f9-45dc-a8fe-06971bbfeb48)


1. El cliente Web debe ser un cliente asíncrono que corra en el browser.
2. La aplicación debe ser multiusuario.
3. El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2
4. LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
5. La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.
6. Entrega archivos estáticos como páginas HTML.
7. Permite configurar el directorio de donde se leerán los archivos estáticos.
8. Permite leer parámetros del query  desde los programas.
9. SparkJava como framework para la aplicación web.
10. Dockerfile y compose para el manejo de imagenes y contenedores con Docker.
11. Debe ser accesible desde internet usando el servicio EC-2 de AWS.
12. La aplicación corre sobre el puerto 4567
13. Los servicios corren sobre los puertos 35001, 35002 y 35003.
14. La base de datos Mongo corre sobre el puerto 27017.

## Diseño de la aplicación

- La aplicación usa SparkJava para correr el servidor Http.
- Desde la clase SparkWebServer.java se puede configurar las respuestas del servidor mediante el uso de funciones Lambda.
- En el directorio \scr\main\java\com\app\lab6\resources\public se pueen añadir archivos estaticos como HTML, CSS, JS o imagenes.
- RRInvoker es el balanceador de carga.
- LogService es la clase encargada de atender las peticiones y comunicarse con la base de datos, para este caso la aplicación cuenta con tres instancias de LogService

# Extensión de la aplicación

- En SparkWebServer.java se pueden añadir nuevas funciones que se desean el servidor de respuesta
- Css para mejorar la vista de la aplicación

## Guia de inicio

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Prerrequisitos

- Java 8
- Maven
- Git
- Navegador web
- Docker
- AWS EC-2

### Instalación

#### Local
Ubiquese en el directorio en donde desea descargar el repositorio

`git clone https://github.com/Mateo0laya/Lab6-AREP--Patterns.git`

Cambie al directorio del repositorio

`cd Lab6-AREP--Patterns`

Compile el proyecto

`mvn clean install`

Inicie el servidor

`docker-compose up --build`

Deberá tener un contenedor en Docker Desktop con 5 imágenes como aparece a continuación:

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/0b45b061-d55d-4344-adc7-9e420df130d5)


#### AWS
Una vez tenga su instancia de EC-2 en ejecución, realice la conexión de su preferencia, en mi caso estoy usando SSH.

Lo primero que debe hacer es actualizar los paquetes de instalación del sistema operativo

`sudo yum update -y`

Ahora debe instalar Docker, Maven, Git y Docker-Compose

`sudo yum install docker`

`sudo yum install maven`

`sudo yum install git`

Para la instalación de Docker-Compose ejecute los siguientes comandos 

`sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose`

`sudo chmod +x /usr/local/bin/docker-compose`

Por último repita el proceso de instalación local en la instancia del EC-2 y debera ver algo a lo mostrado a continuación

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/4881b7eb-4ab1-4dc5-9f9c-7bf411adb417)


## Probando la aplicación
A continuación encuentra un video donde se detalla las pruebas de la aplicación

Para probar la aplicación lo podemos realizar con ejecución en local, o desde una instancia EC-2 de AWS, en este caso optaré por la segunda opción. Una vez con la aplicación corriendo podemos empezar con las pruebas

Desde el navegador accederemos a la dirección DNS proporcionada por AWS para nuestra instancia EC-2, es importante recordar que la aplicación corre sobre el puerto 4567. Del mismo modo debemos acceder usando el protocolo http, en mi caso la dirección es la siguiente, pero en su caso debe variar: http://ec2-34-233-121-200.compute-1.amazonaws.com:4567/

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/e8360808-611e-4491-bbb2-9eba6cd9f21c)

Una vez en la página dispondremos de un campo de texto donde podremos ingresar datos a almacenar, recuerde hacer uso del botón submit dando click, no use la tecla enter
![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/92e50685-1440-4015-9fbb-7c11b27b2aad)

Seguiremos añadiendo datos hasta completar 10 registros

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/dbaf6178-ce94-44c2-b46b-0175f084696c)

En este punto verificaremos el funcionamiento del balanceador de carga en la terminal SSH de la instancia EC-2

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/15128c14-6459-40c9-9b80-059c8bae5bcf)

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/bd4f7e9f-d011-46ab-8337-965365521cf1)

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/f30a3846-b34f-4abb-82fe-b971ba54c0e1)

Como se observa las peticiones son atendidas de manera ordenada y equitativa por los servicios. Una vez añadamos un nuevo registro el primero que añadimos ya no será visible pues unicamente son visibles los ultimos 10 registros

![image](https://github.com/Mateo0laya/Lab6-AREP--Patterns/assets/89365336/1208b91a-1038-443a-95ce-446e0fc7028b)


## Construido con

* [Java](https://www.java.com/es/) - The main programming language
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spark](https://sparkjava.com/) - MicroFramework Web
* [Docker](https://www.docker.com/) - Virtualization Software
* [EC-2 AWS](https://aws.amazon.com/es/ec2/) - cloud Processing

## Version

Version 1.0.0.

## Autor

Mateo Olaya Garzon
