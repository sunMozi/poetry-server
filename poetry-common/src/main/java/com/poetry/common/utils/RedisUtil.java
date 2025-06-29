package com.poetry.common.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis 工具类 - 支持 String、Hash、Set 操作
 * <p>
 * 基于 Spring 的 StringRedisTemplate 封装，
 * 提供常用操作的统一接口，支持过期时间控制及异常处理。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

  private final StringRedisTemplate redisTemplate;

  // ====================== String 操作 =========================

  /**
   * 写入字符串缓存，默认无过期时间
   *
   * @param key   缓存键，非空
   * @param value 缓存值
   * @return 写入是否成功，true成功，false失败
   */
  public boolean set(String key, String value) {
    return set(key, value, -1);
  }

  /**
   * 写入字符串缓存，支持指定过期时间
   *
   * @param key           缓存键，非空
   * @param value         缓存值
   * @param expireSeconds 过期时间，单位秒，<=0表示不过期
   * @return 写入是否成功，true成功，false失败
   */
  public boolean set(String key, String value, long expireSeconds) {
    try {
      if (expireSeconds > 0) {
        redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
      } else {
        redisTemplate.opsForValue().set(key, value);
      }
      return true;
    } catch (Exception e) {
      log.error("Redis set error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 根据键获取字符串缓存值
   *
   * @param key 缓存键，非空
   * @return 缓存值，找不到或异常返回null
   */
  public String get(String key) {
    try {
      return redisTemplate.opsForValue().get(key);
    } catch (Exception e) {
      log.error("Redis get error, key={}, error={}", key, e.getMessage(), e);
      return null;
    }
  }

  // ====================== Hash 操作 =========================

  /**
   * 在 Hash 结构中写入单个 field 的值
   *
   * @param key     Hash 键，非空
   * @param hashKey Hash field 键，非空
   * @param value   field 对应的值
   * @return 操作是否成功，true成功，false失败
   */
  public boolean hPut(String key, String hashKey, String value) {
    try {
      redisTemplate.opsForHash().put(key, hashKey, value);
      return true;
    } catch (Exception e) {
      log.error("Redis hPut error, key={}, hashKey={}, error={}", key, hashKey, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 在 Hash 结构中批量写入字段和值，支持设置过期时间
   *
   * @param key           Hash 键，非空
   * @param map           字段与值的映射关系
   * @param expireSeconds 过期时间，单位秒，<=0表示不过期
   * @return 操作是否成功，true成功，false失败
   */
  public boolean hPutAll(String key, Map<String, String> map, long expireSeconds) {
    try {
      redisTemplate.opsForHash().putAll(key, map);
      if (expireSeconds > 0) {
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
      }
      return true;
    } catch (Exception e) {
      log.error("Redis hPutAll error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 在 Hash 结构中批量写入字段和值，无过期时间
   *
   * @param key Hash 键，非空
   * @param map 字段与值的映射关系
   * @return 操作是否成功，true成功，false失败
   */
  public boolean hPutAll(String key, Map<String, String> map) {
    return hPutAll(key, map, -1);
  }

  /**
   * 获取 Hash 中指定字段的值
   *
   * @param key     Hash 键，非空
   * @param hashKey Hash 字段键，非空
   * @return 字段对应的值，找不到或异常返回 null
   */
  public String hGet(String key, String hashKey) {
    try {
      Object val = redisTemplate.opsForHash().get(key, hashKey);
      return val != null ? val.toString() : null;
    } catch (Exception e) {
      log.error("Redis hGet error, key={}, hashKey={}, error={}", key, hashKey, e.getMessage(), e);
      return null;
    }
  }

  /**
   * 获取 Hash 中所有字段及其对应的值
   *
   * @param key Hash 键，非空
   * @return 所有字段及值的映射，异常时返回空 Map
   */
  public Map<Object, Object> hGetAll(String key) {
    try {
      return redisTemplate.opsForHash().entries(key);
    } catch (Exception e) {
      log.error("Redis hGetAll error, key={}, error={}", key, e.getMessage(), e);
      return Collections.emptyMap();
    }
  }

  /**
   * 删除 Hash 中一个或多个字段
   *
   * @param key      Hash 键，非空
   * @param hashKeys 要删除的字段列表
   * @return 操作是否成功，true成功，false失败
   */
  public boolean hDelete(String key, String... hashKeys) {
    try {
      redisTemplate.opsForHash().delete(key, (Object[]) hashKeys);
      return true;
    } catch (Exception e) {
      log.error("Redis hDelete error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 判断 Hash 中是否存在指定字段
   *
   * @param key     Hash 键，非空
   * @param hashKey 字段键，非空
   * @return 存在返回 true，否则 false
   */
  public boolean hExists(String key, String hashKey) {
    try {
      return redisTemplate.opsForHash().hasKey(key, hashKey);
    } catch (Exception e) {
      log.error("Redis hExists error, key={}, hashKey={}, error={}",
                key,
                hashKey,
                e.getMessage(),
                e);
      return false;
    }
  }

  // ====================== Set 操作 =========================

  /**
   * 向 Set 集合中添加单个成员
   *
   * @param key   Set 键，非空
   * @param value 成员
   * @return 添加成功返回 true，失败返回 false
   */
  public boolean sAdd(String key, String value) {
    try {
      return redisTemplate.opsForSet().add(key, value) > 0;
    } catch (Exception e) {
      log.error("Redis sAdd error, key={}, value={}, error={}", key, value, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 向 Set 集合中添加多个成员
   *
   * @param key    Set 键，非空
   * @param values 成员集合
   * @return 添加成功返回 true，失败返回 false
   */
  public boolean sAdd(String key, Collection<String> values) {
    try {
      return redisTemplate.opsForSet().add(key, values.toArray(new String[0])) > 0;
    } catch (Exception e) {
      log.error("Redis sAdd error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 获取 Set 集合中的所有成员
   *
   * @param key Set 键，非空
   * @return 成员集合，异常返回空集合
   */
  public Set<String> sMembers(String key) {
    try {
      Set<String> members = redisTemplate.opsForSet().members(key);
      return members != null ? members : Collections.emptySet();
    } catch (Exception e) {
      log.error("Redis sMembers error, key={}, error={}", key, e.getMessage(), e);
      return Collections.emptySet();
    }
  }

  /**
   * 从 Set 集合中移除单个成员
   *
   * @param key   Set 键，非空
   * @param value 成员
   * @return 移除成功返回 true，失败返回 false
   */
  public boolean sRemove(String key, String value) {
    try {
      return redisTemplate.opsForSet().remove(key, value) > 0;
    } catch (Exception e) {
      log.error("Redis sRemove error, key={}, value={}, error={}", key, value, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 从 Set 集合中移除多个成员
   *
   * @param key    Set 键，非空
   * @param values 成员集合
   * @return 移除成功返回 true，失败返回 false
   */
  public boolean sRemove(String key, Collection<String> values) {
    try {
      return redisTemplate.opsForSet().remove(key, values.toArray()) > 0;
    } catch (Exception e) {
      log.error("Redis sRemove error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 判断 Set 集合是否包含指定成员
   *
   * @param key   Set 键，非空
   * @param value 成员
   * @return 包含返回 true，不包含或异常返回 false
   */
  public boolean sIsMember(String key, String value) {
    try {
      return redisTemplate.opsForSet().isMember(key, value);
    } catch (Exception e) {
      log.error("Redis sIsMember error, key={}, value={}, error={}", key, value, e.getMessage(), e);
      return false;
    }
  }

  // ====================== 通用操作 =========================

  /**
   * 删除指定缓存键
   *
   * @param key 缓存键，非空
   * @return 删除成功返回 true，失败返回 false
   */
  public boolean delete(String key) {
    try {
      return Boolean.TRUE.equals(redisTemplate.delete(key));
    } catch (Exception e) {
      log.error("Redis delete error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 判断缓存键是否存在
   *
   * @param key 缓存键，非空
   * @return 存在返回 true，不存在或异常返回 false
   */
  public boolean hasKey(String key) {
    try {
      return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    } catch (Exception e) {
      log.error("Redis hasKey error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 设置缓存键过期时间，单位秒
   *
   * @param key           缓存键，非空
   * @param expireSeconds  过期时间，单位秒，<=0表示取消过期
   * @return 设置成功返回 true，失败返回 false
   */
  public boolean expire(String key, long expireSeconds) {
    try {
      if (expireSeconds > 0) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS));
      } else {
        return Boolean.TRUE.equals(redisTemplate.persist(key));
      }
    } catch (Exception e) {
      log.error("Redis expire error, key={}, error={}", key, e.getMessage(), e);
      return false;
    }
  }
}
