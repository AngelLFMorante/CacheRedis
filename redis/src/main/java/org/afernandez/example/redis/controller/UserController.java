package org.afernandez.example.redis.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar usuarios con soporte de caché.
 */
@RestController
@RequestMapping("/users")
@CacheConfig(cacheNames = "users") // Define la caché con el nombre "users".
class UserController {
    private final Map<Integer, String> userDatabase = new HashMap<>();

    /**
     * Obtiene un usuario por su ID. Usa caché para mejorar el rendimiento.
     * Si el usuario ya está almacenado en caché, se devuelve directamente desde Redis.
     *
     * @param id ID del usuario.
     * @return Nombre del usuario o un mensaje si no se encuentra.
     */
    @GetMapping("/{id}")
    @Cacheable(value = "users", key = "#id")  // Si el usuario ya está en caché, se devuelve sin ejecutar el método.
    public String getUser(@PathVariable("id") Integer id) {
        simulateSlowService(); // Simula una consulta lenta a base de datos.
        return userDatabase.getOrDefault(id, "Usuario no encontrado");
    }

    /**
     * Agrega o actualiza un usuario en la base de datos y lo almacena en caché.
     *
     * @param id   ID del usuario.
     * @param name Nombre del usuario.
     * @return Mensaje de confirmación.
     */
    @PostMapping("/{id}")
    @CachePut(value = "users", key = "#id") // Actualiza la caché con el nuevo valor.
    public String addUser(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        userDatabase.put(id, name);
        return "Usuario " + name + " agregado exitosamente.";
    }

    /**
     * Elimina un usuario de la base de datos y lo borra de la caché.
     *
     * @param id ID del usuario a eliminar.
     * @return Mensaje de confirmación.
     */
    @DeleteMapping("/{id}")
    @CacheEvict(value = "users", key = "#id") // Elimina el usuario de la caché.
    public String deleteUser(@PathVariable Integer id) {
        userDatabase.remove(id);
        return "Usuario eliminado.";
    }

    /**
     * Simula una operación lenta, como una consulta a base de datos.
     * Esto ayuda a demostrar cómo la caché mejora el rendimiento.
     */
    private void simulateSlowService() {
        try {
            Thread.sleep(3000); // Simula un retraso de 3 segundos.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
