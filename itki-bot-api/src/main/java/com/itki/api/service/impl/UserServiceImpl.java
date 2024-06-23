package com.itki.api.service.impl;

import com.itki.api.model.User;
import com.itki.api.repository.UserRepository;
import com.itki.api.service.UserService;
import java.time.Duration;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {
  @Value("#{T(java.time.Duration).ofMillis('${JWT_TOKEN_TTL}').multipliedBy(5)}")
  private Duration userCacheExpiration;

  @Value("${REDIS_FEATURE}")
  private Boolean redisFeature;
  private final UserRepository userRepository;
  private final ValueOperations<String, User> valueOps;


  public UserServiceImpl(
      UserRepository userRepository,
      @Qualifier("userByLoginCache")
      RedisTemplate<String, User> userByLoginCache
  ) {
    super(userRepository, User.class.getSimpleName());
    this.userRepository = userRepository;
    this.valueOps = userByLoginCache.opsForValue();
  }

  @Override
  public Optional<User> findByLogin(String login) {
    if (redisFeature) {
      long startOperationTime = System.currentTimeMillis();
      Optional<User> byLoginWithCache = findByLoginWithCache(login);
      log.info(
          "findByLoginWithCache took {} ms",
          System.currentTimeMillis() - startOperationTime
      );
      return byLoginWithCache;
    }
    long startOperationTime = System.currentTimeMillis();
    Optional<User> userByLogin = userRepository.findUserByLogin(login);
    log.info(
        "findByLogin without cache took {} ms",
        System.currentTimeMillis() - startOperationTime
    );
    return userByLogin;
  }

  private Optional<User> findByLoginWithCache(String login) {
    Optional<User> user = Optional.ofNullable(valueOps.get(login));
    if (user.isEmpty()) {
      user = userRepository.findUserByLogin(login);
      user.ifPresent(u -> valueOps.set(login, u, userCacheExpiration));
    }
    return user;
  }
}
