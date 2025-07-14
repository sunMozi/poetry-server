package com.poetry.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.StringUtils;

public class SlugUtils {

  /**
   * 生成 slug 主方法
   * @param title 标题
   * @param existsCheckFunc 用于检测 slug 是否存在的函数接口
   * @return 唯一 slug
   */
  public static String generateUniqueSlug(String title, SlugExistsCheck existsCheckFunc) {
    if (StringUtils.isBlank(title)) {
      throw new IllegalArgumentException("标题不能为空");
    }

    String baseSlug = toSlug(title);
    String uniqueSlug = baseSlug;
    int count = 1;

    // 唯一性检查
    while (existsCheckFunc.exists(uniqueSlug)) {
      uniqueSlug = baseSlug + "-" + count++;
    }

    return uniqueSlug;
  }

  /**
   * 转换标题为基础 slug，不保证唯一
   * @param title 标题
   * @return slug
   */
  public static String toSlug(String title) {
    StringBuilder slugBuilder = new StringBuilder();

    for (char ch : title.toCharArray()) {
      if (Character.isWhitespace(ch)) {
        slugBuilder.append("-");
      } else if (isAsciiLetterOrDigit(ch)) {
        slugBuilder.append(Character.toLowerCase(ch));
      } else if (isChinese(ch)) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(ch);
        if (pinyins != null && pinyins.length > 0) {
          // 去掉数字音调
          slugBuilder.append(pinyins[0].replaceAll("\\d", "")).append("-");
        }
      } // 非法字符自动跳过
    }

    String slug = slugBuilder.toString();

    // 连续横线合并，去除首尾横线
    slug = slug.replaceAll("-+", "-").replaceAll("^-|-$", "");

    return slug;
  }

  private static boolean isAsciiLetterOrDigit(char ch) {
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
  }

  private static boolean isChinese(char ch) {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
    return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
        ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
        ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
  }

  /**
   * 函数式接口：检查 slug 是否已存在
   */
  @FunctionalInterface
  public interface SlugExistsCheck {

    boolean exists(String slug);
  }
}
