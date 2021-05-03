# mutant

El proyecto hace el uso del framework SpringBoot y Java11. La aplicación valida si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical. Se despliega en Google App Engine stdandard.

## Setup

1. [Configure su entorno de desarrollo de Java](https://cloud.google.com/java/docs/setup)

2. Clonar el repositorio:
    git clone https://github.com/jaestevez/mutant.git

3.Obtener credenciales de autenticación.
    Crea las credenciales locales con el siguiente comando:
        gcloud auth application-default login

## Deploying

```bash
gcloud app deploy
```

Visualizar app, usa el comando:
```
gcloud app browse
```
O navega a `https://<your-project-id>.appspot.com`.