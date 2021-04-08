# IP Monitor - MercadoLibre

# INDICE

## 1 - DESCRIPCIÓN DEL PROYECTO
## 2 - COMO CONSTRUIR, CORRER Y PROBAR EL API EN DOCKER
## 3 - COMO PROBARLO
## 4 - COMO REVISAR EL REPORTE DE CUBRIMIENTO 
## 5 - COMO CONFIGURARLO 

# 1 - DESCRIPCIÓN DEL PROYECTO
* Para esta solución se uso un stack de tecnologias compuesto por spark java, morphia, mongo db, dotenv, docker, Gson.
* 
* com.mercadolibre.ipmonitor.App: Clase de entrada en la cual se expone el puerto 3000 y se inicia la base de datos.

* com.mercadolibre.ipmonitor.builder.IPAddressBuilder: Esta clase que fue hecha con un pseudo patron creacional builder, se encarga de la creación de los datos relacionados con ips, paises, monedas y tasas de cambio. Es quien orquesta si es necesario obtener la información desde uno de los tres servicios externos o si la información se encuentra disponible en la base de datos local no relacional mongo (el nombre de la base de datos es mercadolibre).

* com.mercadolibre.ipmonitor.esl: Este paquete contiene una serie de clases encargadas de obtener la información relacionada con ips, países, monedas y tasas de cambio desde los servicio externos provistos en la prueba. Este paquete es suceptible a mejoras en el manejo de errores. Por simplicidad se utilizó java.net.HttpURLConnection y javax.net.ssl.HttpsURLConnection.

* com.mercadolibre.ipmonitor.filter: Por simplicidad y para controlar el trafico en el API se utilizó Basic authentication. En este paquete se encuentra un filtro de spark que se encargada de validar la autenticación, así como una clase que encapsula el nombre de usuario y su password. 

* com.mercadolibre.ipmonitor.model: Este paquete contine basicamente nuestras entidades de negocio, IP, PAÍS y MONEDA.

* com.mercadolibre.ipmonitor.response: Este paquete contiene una enumeración y una clase usadas para las respuestas provistas por los endpoints del API.

* com.mercadolibre.ipmonitor.router: Para evitar el uso de un framework mucho mas pesado y complicado como Spring se escogió Spark Java para la implementacion del API.
Basicamente se esta exponiendo dos endpoints:

 GET  /ip/info/:ip
 post /ip/ban/:ip

* com.mercadolibre.ipmonitor.service: Servicio encargado de ejecutar la logica de negocio.

* com.mercadolibre.ipmonitor.utils: Utilitarios.

# 2 - COMO CONSTRUIR, CORRER Y PROBAR EL API EN DOCKER
* Este proyecto utiliza docker-compose para levantar dos containers, uno el de la base de datos mongo y otro en el cual se encuentra el API.
* Se puede levantar el API utilizando el siguiente comando desde la raiz del proyecto

## docker-compose up --build --remove-orphans

- Se debe asegurar que no hayan instancias locales de mongo.
- El API estara escuchando peticiones en el puerto 3000.
- La base de datos que escuchando peticiones en el puerto 27017.
- Se incluye una coleccion de Postman, con dos requests de ejemplo a cada uno de los endpoints provistos. Esta colección se encuentra en <PROJECT_ROOT>/postman.

# 3 - COMO PROBARLO
* PENDIENTE pruebas unitarias, de integración y funcionales.

# 4 - COMO REVISAR EL REPORTE DE CUBRIMIENTO
* Si tuviera pruebas unitarias y de integración, el reporte de cubrimiento podría ser encontrado en <PROJECT-ROOT>/target/site/jacoco/index.html, despues de la ejecucion de los tests.

# 5 - COMO CONFIGURARLO
* Para la configuración del proyecto se utilizó dotenv. Las configuraciones se encuentran en el archivo <PROJECT-ROOT>/src/main/resources/.env.
* Dentro de las configuracion encontras las siguientes variables:

- DATABASE_URI Corresponde a la URI para establecer la conexion con la base de datos mongo, por ejemplo en nuestro container docker se usa: mongodb://mongo:27017

- DATABASE_NAME mercadolibre

- EXPECTED_USER Nombre de usuario para la autenticación basica con la cual el API controla el acceso a los endpoints, por ej: admin

- EXPECTED_PASSWD Password de usuario para la autenticación basica con la cual el API controla el acceso a los endpoints, por ej: admin.4321

- COUNTRY_INFO_SERVICE URL del servicio de información de países, debe tener el comodín <IP_ADDRESS> el cual es reemplazado en tiempo de ejecución por la ip a consultar. Por ej: https://api.ip2country.info/ip?<IP_ADDRESS>

- CURRENCY_INFO_SERVICE URL del servicio de información de monedas debe tener el comodín <COUNTRY> el cual es reemplazado en tiempo de ejecución por el codigo de tres letras del país a consultar. Por ej: https://restcountries.eu/rest/v2/alpha/<COUNTRY>

- RATE_INFO_SERVICE_ACCESS_KEY LLave de acceso para el servicio de información de tasas de cambio, por ej: 12ed59c9bbdf433ffbd6f5317b2efa88

- RATE_INFO_SERVICE URL del servicio de información de monedas debe tener los comodines <ACCESS_KEY>, reemplazado por el valor de RATE_INFO_SERVICE_ACCESS_KEY y <CURRENCY> el cual es reemplazado en tiempo de ejecución por el codigo de tres letras de la moneda a consultar. Por ej:http://data.fixer.io/api/latest?access_key=<ACCESS_KEY>&base=EUR&symbols=USD,<CURRENCY>
 