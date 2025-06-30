package com.poetry.common.core.pojo;


import lombok.Data;

/**
 * @author moZiA
 */
@Data
public class TokenPayload {

  private Long userId;
  private Integer userType;
  private String source;
  private Long issuedAt;

  public TokenPayload(Long userId, Integer userType, String source) {
    this.userId = userId;
    this.userType = userType;
    this.source = source;
    this.issuedAt = System.currentTimeMillis();
  }

}
