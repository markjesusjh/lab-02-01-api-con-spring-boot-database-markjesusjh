# lab-02-01-api-con-spring-boot-database-markjesusjh

1- Clonar el repositorio

2 - Iniciar el proyecto con las siguientes caracteristicas

[![1.jpg](https://i.postimg.cc/brD55BSS/1.jpg)](https://postimg.cc/fSs5t5KM)

3- Agregar las siguientes propiedades en `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/crud
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```
Con ayuda de Postman verificamos el funcionamiento de nuestra API

[![2.jpg](https://i.postimg.cc/pLJPkmHb/2.jpg)](https://postimg.cc/bZdXwNr3)

Guardamos
[![4.jpg](https://i.postimg.cc/rwMkdqth/4.jpg)](https://postimg.cc/6yjSgJcR)

Obtenemos
[![5.jpg](https://i.postimg.cc/m2GxqTVb/5.jpg)](https://postimg.cc/gr4BLFf5)

Podemos podemos ver que esta creado en Database con id 6
[![6.jpg](https://i.postimg.cc/VvQh3Fq7/6.jpg)](https://postimg.cc/gXs4h8BR)

Lo actualizamos cambiandlo el nombre
[![7.jpg](https://i.postimg.cc/vHyjh3HT/7.jpg)](https://postimg.cc/vDP3H7nw)

Get para visualizar la actualizacion
[![8.jpg](https://i.postimg.cc/1t6CcNkD/8.jpg)](https://postimg.cc/JDr5Rt4h)

Metodo Delete para eliminar 
[![9.jpg](https://i.postimg.cc/YqtnY0bm/9.jpg)](https://postimg.cc/DmYPKvV2)

Y finalmente podemos ver su eliminacion en la Database
[![10.jpg](https://i.postimg.cc/3w0nYvJz/10.jpg)](https://postimg.cc/5H1wmjzm)

De la misma forma sucede con Playlist
