package com.poetry.common.config;

import com.poetry.common.interceptor.JwtTokenAdminInterceptor;
import com.poetry.common.interceptor.RepeatSubmitInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author moZiA
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
  private final RepeatSubmitInterceptor repeatSubmitInterceptor;


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

    registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");

  }


}
