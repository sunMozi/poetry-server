package com.poetry.common.config;

import com.poetry.common.interceptor.JwtTokenAdminInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author moZiA
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Resource
  private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/login",
                                 "/error",
                                 "/favicon.ico",
                                 "/swagger-ui.html",
                                 "/doc.html/**",
                                 "/swagger-ui/**",
                                 "/v3/api-docs/**");
  }
}
