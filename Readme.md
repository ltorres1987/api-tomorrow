# Services backend Wholemeaning

### Descripción

Desafío Wholemeaning - Backend

### Construcción 🛠️
* **Language:** Kotlin
* **Framework:** Ktor, Redis

## Requerimientos
- Docker
- Git
- Terminal(Cmder,cmd)

## Instalación

Pasos:
- **Nota: Verificar que no este en uso el puerto ```8080```**

1. Clone el proyecto.
2. Renombrar ```.env.example``` con el nombre ```.env``` que se encuentra en la carpeta **deployments** del proyecto.
3. Renombrar ```docker-compose.example.yml``` con el nombre ```docker-compose.yml``` que se encuentra en la carpeta **deployments** del proyecto.
4. Haciendo uso del terminal ubicarse dentro de la carpeta **deployments** del proyecto y construya e implemente los servicos con el siguiente comando: ```docker-compose up -d --build```
5. Acceda al Cli de redis con el siguiente comando para consultar los keys : ```docker run -it --rm --network deployments_application bitnami/redis:latest redis-cli -h backend-service-redis```
   1. una vez dentro del contenedor debe autenticarse con el siguiente comando ```AUTH 1234*```
   2. Uso de comandos para la revisión.
      1. ```keys *``` permite visualizar todas las keys
      2. ```get key``` permite obtener el contenido del key

En caso que requiera detener los docker ejecute el siguiente comando:
- Detener docker: ```docker-compose down```

## Funcionamiento y uso

- Una vez realizado el despliegue de los servicios, los cuales cuentan con los siguientes componentes:
  1. api - **backend-service-api-service**
     - Api Rest: desde la cual se pueda consultar cualquiera de las localidades, y retorna el valor guardado en el cache de redis.
       - Localidades
         1. santiago
         2. zurich 
         3. auckland 
         4. sidney 
         5. londres 
         6. georgia
     - cURL: Endpoints meteorológica para la consulta de localidades:
       ```
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/santiago'
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/zurich'
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/auckland'
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/sidney'
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/londres'
       curl --location --request GET 'http://127.0.0.1:8080/tomorrow/georgia'
       ```
     - Documentación
       ```
       https://documenter.getpostman.com/view/10015938/2s847HQCzk#d0b04db2-40c3-4aa8-b766-201eacd8f338
       ```
        
  2. crontab - **backend-service-crontab-service**
     - Crontab que cada 5 minutos consulta las localidades en la api meteorológica Tomorrow y las almacena en redis.
  3. redis - **backend-service-redis**
     - Cache para almacenar las localidades y logs
