# mutant

La aplicación valida si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical. 

Hace uso del framework SpringBoot y Java11, se despliega en Google App Engine standard y usa como dase de datos Firestore como Datastore.
  
## Configuración

1. [Configure su entorno de desarrollo de Java](https://cloud.google.com/java/docs/setup)

2. Clonar el repositorio:
    ```bash
    git clone https://github.com/jaestevez/mutant.git
    ```
3. Crea las credenciales locales con el siguiente comando:
    ```bash
    gcloud auth application-default login
    ```
        

## Despliegue

Para desplegar el proyecto usa el comando:
```bash
gcloud app deploy
```

Visualizar app, usa el comando:
```
gcloud app browse
```
O navega a `https://<your-project-id>.appspot.com`.

## Probar API

Realizar una validación del ADN:

 La entrada es un arreglo que debe cumplir con las siguientes reglas:
- Las letras validas son A, T, C y G.
- El numero de filas y columnas deben ser iguales.
```
POST https://<your-project-id>.appspot.com/mutant
{
    "dna": [
       "ATGCGA",
        "CTGAGA",
        "TACGGA",
        "ATGCAA",
        "CTTTTA",
        "TCCATC"
    ]
}
```
La respuesta sera 200 en caso de ser un mutante, 403 en caso de ser un humano y 400 en caso de tener problemas en los datos de entrada. 

Obtener estadisticas de las validaciones realizadas:
```
GET https://<your-project-id>.appspot.com/stats
```
Se obtiene como resultado:
```
{
    "ratio": 0.5,
    "count_mutant_dna": 5,
    "count_human_dna": 5
}
```
## Licencia
MIT