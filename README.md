# Aplicación de Películas - Proyecto Android

## Descripción

Este proyecto fue desarrollado en el marco del Seminario Android de la carrera "Tecnología Universitaria en Desarrollo de Aplicaciones Informáticas" de la Facultad de Ciencias Exactas de la UNCPBA. 
Se llevó a cabo el desarrollo de una aplicación de películas para dispositivos Android. La aplicación utiliza la API de [The Movie Database (TMDB)](https://www.themoviedb.org/) para brindar 
información detallada sobre películas, la cual incluye títulos, imágenes de portada, sinopsis, rating e idioma.

La app permite a los usuarios explorar una lista de "películas populares" y obtener información detallada sobre cada una. 
Para acceder a la API de TMDB y obtener los datos de las películas, es necesario registrarse en el sitio y obtener un token de acceso.

## Funcionalidades Principales

1. **Pantalla principal - Lista de películas populares**:
   - La aplicación presenta una lista de las películas populares obtenidas a través de la API de TMDB.
   - Cada elemento de la lista muestra el título de la película, una imagen de portada y el idioma en el que se puede ver el film.
   - Al hacer click en una película, se navega a una pantalla de detalles para ver más información sobre la misma.

2. **Pantalla de detalles de la película**:
   - Esta pantalla amplia la información de la película seleccionada, incluyendo:
     - Título de la película.
     - Imagen de portada.
     - Resumen de la trama.
     - Géneros de la película.
     - Calificación (rating).

3. **Indicador de carga y manejo de errores**:
   - Mientras se realiza una consulta a la API (para obtener la lista de películas), se muestra un indicador de carga en pantalla.
   - En caso de error en la consulta, se notifica al usuario, mostrando el error y ofreciendo la opción de reintentar la operación.

4. **Layout adaptativo**:
   - En modo vertical, las películas se muestran en una lista vertical, una debajo de la otra.
   - La aplicación está optimizada para cambios de orientación de pantalla. Cuando el usuario gira el dispositivo a modo horizontal
     con el objetivo de optimizar el espacio disponible en la pantalla principal, el diseño cambia para mostrar las películas en dos columnas.
   
## Tecnologías Usadas

- **Lenguaje de programación**: Kotlin
- **API de películas**: The Movie Database (TMDB)
- **Manejo de imágenes**: Glide
- **Consumo de APIs**: Retrofit

## Requisitos

- **API Key de TMDB**: Para utilizar la API de TMDB, hay que registrarse en [TMDB](https://www.themoviedb.org/) y obtener un Token al cual se accede en forma gratuita.
  En este caso el Token se encuentra disponible en el software que se presenta.
- **Android Studio**: Con el objetivo de ejecutar la aplicación se debe trabajar en Android Studio.
