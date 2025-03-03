# Proyecto: Implementación de Caché con Redis en Spring Boot

Este repositorio contiene un proyecto de ejemplo que utiliza **Spring Boot** y **Redis** para implementar una capa de caché, lo que mejora el rendimiento y reduce la carga en la base de datos. Es útil para practicar o prepararse para entrevistas técnicas en las que se requiera implementar un sistema con caché.

---

## 🚀 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cache** (Para la gestión de caché)
- **Spring Data Redis** (Para la conexión con Redis)
- **Lettuce** (Cliente para interactuar con Redis)
- **Maven** (Gestor de dependencias)
- **Docker Desktop** (Para ejecutar Redis en contenedor)

---

## 🛠️ Cómo Levantar la Aplicación

### 1️⃣ Iniciar Redis con Docker
Si tienes Docker Desktop instalado, ejecuta el siguiente comando para levantar Redis:
```sh
  docker run --name redis -p 6379:6379 -d redis
```
Esto creará un contenedor con Redis ejecutándose en el puerto 6379.

### 2️⃣ Configurar la Aplicación
Asegúrate de que `application.properties` tenga la configuración correcta:
```properties
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### 3️⃣ Compilar y Ejecutar el Proyecto
Ejecuta el siguiente comando en la terminal para levantar la aplicación:
```sh
  mvn spring-boot:run
```
La aplicación se iniciará en `http://localhost:8080`.

---

## 📩 Endpoints y Curl de Prueba
Puedes probar la aplicación usando Postman o con `curl` desde la terminal.

### ✅ Agregar un Usuario a Caché
```sh
  curl -X POST "http://localhost:8080/users/1?name=Juan"
```

### 🔍 Obtener un Usuario (Desde Caché si ya existe)
```sh
  curl -X GET "http://localhost:8080/users/1"
```

### ❌ Eliminar un Usuario de Caché
```sh
  curl -X DELETE "http://localhost:8080/users/1"
```

---

## 🎯 Preguntas Frecuentes en Entrevistas Técnicas

### 1️⃣ **¿Qué es Redis y para qué se usa?**
Redis es una base de datos en memoria que se usa principalmente como **caché** para mejorar el rendimiento de las aplicaciones al reducir el acceso a bases de datos más lentas.

### 2️⃣ **¿Cuál es la diferencia entre @Cacheable, @CachePut y @CacheEvict en Spring Boot?**
- `@Cacheable`: Guarda el resultado de un método en caché y lo devuelve sin ejecutar el método nuevamente.
- `@CachePut`: Actualiza la caché con el nuevo valor generado por el método.
- `@CacheEvict`: Elimina un elemento de la caché.

### 3️⃣ **¿Cómo manejar la expiración de caché en Redis con Spring Boot?**
Puedes configurar la expiración en `RedisCacheConfiguration`, por ejemplo:
```java
RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
```
Esto hace que los datos en caché expiren en 10 minutos.

### 4️⃣ **¿Cuáles son las ventajas y desventajas de usar Redis como caché?**
✅ Ventajas:
- Velocidad extremadamente rápida.
- Reduce la carga en la base de datos.
- Soporta estructuras de datos como listas y conjuntos.

❌ Desventajas:
- Consumo alto de memoria.
- No es persistente por defecto (puede perder datos en caso de apagón).

### 5️⃣ **¿Cómo asegurarse de que los datos en caché estén actualizados?**
- Usar `@CachePut` para actualizar los datos en caché al mismo tiempo que en la base de datos.
- Configurar una política de expiraçón adecuada.
- Utilizar `@CacheEvict` cuando sea necesario invalidar la caché manualmente.

---

## 📜 Licencia MIT
Este proyecto está bajo la licencia **MIT**, lo que significa que cualquier persona puede usar, modificar y distribuir el código sin restricciones. La idea es compartir conocimiento y ayudar a otros desarrolladores en sus entrevistas técnicas.

---

## 🤝 Contribuciones
Si quieres mejorar este proyecto, puedes hacer un **fork** y enviar un **pull request**. Toda ayuda es bienvenida. 🙌

Si te fue útil, dale ⭐ en GitHub. ¡Buena suerte en tu entrevista! 🚀

