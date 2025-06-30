package com.poetry.common.interceptor;


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
 * @description
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

  @Resource
  private TokenUtil tokenUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    String token = request.getHeader("token");
    Claims claims = tokenUtil.parseTokenSafe(token);
    Object o = claims.get("userId");
    Object o1 = claims.get("userType");
    TokenPayload tokenPayload = new TokenPayload(Long.parseLong(o.toString()),
                                                 Integer.parseInt(o1.toString()),
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