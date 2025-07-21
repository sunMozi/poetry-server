package com.poetry.common.interceptor;

import com.poetry.common.annotation.NoRepeatSubmit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class RepeatSubmitInterceptor implements HandlerInterceptor {

  private final RedisTemplate<String, String> redisTemplate;


  private static final String REDIS_KEY_PREFIX = "repeat_submit:";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (!(handler instanceof HandlerMethod handlerMethod)) {
      return true;
    }

    // 只处理 POST / PUT 请求
    String method = request.getMethod().toUpperCase();
    if (!("POST".equals(method) || "PUT".equals(method))) {
      return true;
    }

    NoRepeatSubmit annotation = handlerMethod.getMethodAnnotation(NoRepeatSubmit.class);
    long interval = annotation != null ? annotation.interval() : 3000L;

    String token = request.getHeader("Authorization");
    if (StringUtils.isBlank(token)) {
      token = request.getRemoteAddr();
    }

    String path = request.getRequestURI();
    String redisKey = REDIS_KEY_PREFIX + token + ":" + path;

    Boolean isFirst = redisTemplate.opsForValue()
                                   .setIfAbsent(redisKey, "1", interval, TimeUnit.MILLISECONDS);

    if (Boolean.FALSE.equals(isFirst)) {
      log.warn("重复请求拦截：method={}, path={}, token={}", method, path, token);
      response.setStatus(429);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{\"message\": \"请求过于频繁，请稍后再试\"}");
      return false;
    }

    return true;
  }
}
