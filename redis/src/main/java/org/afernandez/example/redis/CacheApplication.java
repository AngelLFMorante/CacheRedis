package org.afernandez.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Clase principal de la aplicación Spring Boot con caché habilitada.
 */
@SpringBootApplication
@EnableCaching // Habilita la funcionalidad de caché en Spring Boot.
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
