package com.poetry.common.utils;

import com.poetry.common.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * JWT 工具类 - 支持生成、校验、解析
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUtil {

  private final JwtProperties jwtProperties;

  /**
   * 生成 JWT Token
   *
   * @param claims        自定义声明信息（如 userId, userType 等）
   * @param expireSeconds 过期时间（秒），<=0 使用默认配置时间
   * @return 生成的 Token 字符串
   */
  public String generateToken(Map<String, Object> claims, long expireSeconds) {
    SecretKey key = getSecretKey();
    Date now = new Date();
    Date expiry = new Date(now.getTime() + (expireSeconds > 0 ? expireSeconds
                                                : jwtProperties.getExpireSeconds()) * 1000);

    return Jwts.builder()
               .setClaims(claims)
               .setIssuedAt(now)
               .setExpiration(expiry)
               .signWith(key, SignatureAlgorithm.HS256)
               .compact();
  }

  /**
   * 生成用户 Token 示例
   *
   * @param userId 用户 ID
   * @param userType 用户类型
   * @param expireSeconds 过期秒数，<=0 用默认值
   * @return Token 字符串
   */
  public String generateUserToken(Long userId, Integer userType, long expireSeconds) {
    Map<String, Object> claims = Map.of("userId", userId, "userType", userType);
    return generateToken(claims, expireSeconds);
  }

  /**
   * 生成用户 Token 示例 默认时间
   *
   * @param userId 用户 ID
   * @param userType 用户类型
   * @return Token 字符串
   */
  public String generateUserToken(Long userId, Integer userType) {
    Map<String, Object> claims = Map.of("userId", userId, "userType", userType

    );
    return generateToken(claims, jwtProperties.getExpireSeconds());
  }


  /**
   * 校验 Token 是否合法并返回解析结果
   *
   * @param token 待校验的 Token
   * @return Claims 数据
   * @throws JwtException 如果 Token 无效、过期、签名不正确等
   */
  public Claims validateToken(String token) throws JwtException {
    SecretKey key = getSecretKey();
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  /**
   * 尝试解析 Token，捕获异常，避免直接抛出
   *
   * @param token 待解析 Token
   * @return 解析结果 Claims，异常时返回 null
   */
  public Claims parseTokenSafe(String token) {
    try {
      return validateToken(token);
    } catch (ExpiredJwtException e) {
      log.warn("Token 已过期: {}", token);
    } catch (JwtException e) {
      log.warn("Token 校验失败: {}, error: {}", token, e.getMessage());
    }
    return null;
  }

  /**
   * 构建 SecretKey 对象
   *
   * @return SecretKey
   */
  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
  }
}
