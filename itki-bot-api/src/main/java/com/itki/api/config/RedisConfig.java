package com.itki.api.config;

import com.itki.api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
  @Value("${REDIS_HOSTNAME}")
  private String redisHostName;
  @Value("${REDIS_PORT}")
  private int redisPort;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration =
        new RedisStandaloneConfiguration(redisHostName, redisPort);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  public RedisTemplate<String, User> userByLoginCache(
      JedisConnectionFactory jedisConnectionFactory
  ) {
    RedisTemplate<String, User> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    return template;
  }

  @Bean
  public RedisTemplate<String, String> tokenBlackListCache(
      JedisConnectionFactory jedisConnectionFactory
  ) {
    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    return template;
  }
}
