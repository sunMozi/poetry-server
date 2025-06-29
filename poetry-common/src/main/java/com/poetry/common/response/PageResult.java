package com.poetry.common.response;

/**
 * @author moZiA
 * @date 2025/6/14 20:06
 * @description
 */


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用分页结果封装类
 *
 * @param <T> 分页数据项类型
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

  /**
   * 总记录数
   */
  private long totalRows;

  /**
   * 总页数
   */
  private int totalPages;

  /**
   * 当前页码，从 1 开始
   */
  private int pageNum;

  /**
   * 每页大小
   */
  private int pageSize;

  /**
   * 当前页数据条数
   */
  private int size;

  /**
   * 当前页数据列表
   */
  @Builder.Default
  private List<T> rows = Collections.emptyList();

  /**
   * 构造分页结果的静态工厂方法
   */
  public static <T> PageResult<T> of(long totalRows, int pageNum, int pageSize, List<T> data) {
    int totalPages = (int) Math.ceil((double) totalRows / pageSize);
    int size = data != null ? data.size() : 0;
    return PageResult.<T>builder()
                     .totalRows(totalRows)
                     .pageNum(pageNum)
                     .pageSize(pageSize)
                     .totalPages(totalPages)
                     .size(size)
                     .rows(data)
                     .build();
  }
}
