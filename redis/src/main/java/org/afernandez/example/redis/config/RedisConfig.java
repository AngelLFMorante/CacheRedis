package org.afernandez.example.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Configuración de Redis para habilitar la caché en la aplicación.
 */
@Configuration
public class RedisConfig {

    /**
     * Crea una conexión a Redis utilizando Lettuce.
     *
     * @return RedisConnectionFactory para manejar conexiones con Redis.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * Configura el administrador de caché utilizando Redis.
     *
     * @param redisConnectionFactory La conexión a Redis.
     * @return Un RedisCacheManager configurado con opciones personalizadas.
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Define el tiempo de vida de los datos en caché (10 minutos).
                .disableCachingNullValues() // Evita almacenar valores nulos en caché.
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // Serializa los valores en formato JSON.

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }
}

