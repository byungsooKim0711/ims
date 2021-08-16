package org.kimbs.ims.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private final ReactiveRedisConnectionFactory reactiveRedisConnectionFactory;

    public RedisConfig(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        this.reactiveRedisConnectionFactory = reactiveRedisConnectionFactory;
    }

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate() {
        RedisSerializationContext<String, String> redisSerializationContext = RedisSerializationContext
                .<String, String>newSerializationContext()
                .key(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .value(new StringRedisSerializer())
                .hashValue(new StringRedisSerializer())
                .build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, redisSerializationContext);
    }
}
