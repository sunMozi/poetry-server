package com.poetry.common.interceptor;

import com.poetry.common.annotation.RequireAdminToken;
import com.poetry.common.context.BaseContext;
import com.poetry.common.core.pojo.TokenPayload;
import com.poetry.common.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author moZiA
 * @date 2025/5/14 13:56
 * @description 仅拦截标注了 @RequireAdminToken 的接口
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

  @Resource
  private TokenUtil tokenUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod handlerMethod)) {
      return true;
    }

    // 判断类或方法上是否有 RequireAdminToken 注解
    boolean hasAnnotation =
        handlerMethod.getMethod().isAnnotationPresent(RequireAdminToken.class) ||
            handlerMethod.getBeanType().isAnnotationPresent(RequireAdminToken.class);

    if (!hasAnnotation) {
      // 没有注解，直接放行
      return true;
    }

    // 有注解时，进行 token 校验
    String token = request.getHeader("token");
    if (token == null || token.isBlank()) {
      log.warn("请求缺少 token");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Unauthorized: missing token");
      return false;
    }

    Claims claims = tokenUtil.parseTokenSafe(token);
    if (claims == null) {
      log.warn("无效 token");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Unauthorized: invalid token");
      return false;
    }

    Object userIdObj = claims.get("userId");
    Object userTypeObj = claims.get("userType");

    if (userIdObj == null || userTypeObj == null) {
      log.warn("token 缺少必要字段");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Unauthorized: invalid token payload");
      return false;
    }

    TokenPayload tokenPayload = new TokenPayload(Long.parseLong(userIdObj.toString()),
                                                 Integer.parseInt(userTypeObj.toString()),
                                                 "");

    BaseContext.set(tokenPayload);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler,
                              Exception ex) {
    BaseContext.remove();
  }
}
