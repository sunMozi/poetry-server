package com.poetry.common.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 邮件发送工具类
 * 支持 HTML 发送及基于 Thymeleaf 模板渲染发送
 * @author moZiA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MailUtil {

  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String fromAddress;

  /**
   * 发送单个 HTML 邮件（纯字符串内容）
   */
  public void sendHtmlMail(String to, String subject, String htmlContent) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

      helper.setFrom(fromAddress);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true);

      mailSender.send(mimeMessage);
      log.info("HTML 邮件已成功发送给: {}, 主题: {}", to, subject);
    } catch (MessagingException e) {
      log.error("发送 HTML 邮件失败: {}, 主题: {}", to, subject, e);
      throw new RuntimeException("邮件发送失败，请稍后重试");
    }
  }

  /**
   * 群发 HTML 邮件（纯字符串内容）
   */
  public void sendHtmlMail(List<String> toList, String subject, String htmlContent) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

      helper.setFrom(fromAddress);
      helper.setTo(toList.toArray(new String[0]));
      helper.setSubject(subject);
      helper.setText(htmlContent, true);

      mailSender.send(mimeMessage);
      log.info("HTML 群发邮件已成功发送给: {}, 主题: {}", toList, subject);
    } catch (MessagingException e) {
      log.error("群发 HTML 邮件失败: {}, 主题: {}", toList, subject, e);
      throw new RuntimeException("邮件群发失败，请稍后重试");
    }
  }

  /**
   * 通过 Thymeleaf 模板发送单个 HTML 邮件
   *
   * @param to 收件人邮箱
   * @param subject 邮件主题
   * @param template 模板路径（相对于 resources/templates，后缀不带 .html）
   * @param variables 模板变量Map
   */
  public void sendHtmlMailWithTemplate(String to,
                                       String subject,
                                       String template,
                                       Map<String, Object> variables) {
    try {
      Context context = new Context();
      context.setVariables(variables);
      String htmlContent = templateEngine.process(template, context);

      sendHtmlMail(to, subject, htmlContent);
    } catch (Exception e) {
      log.error("基于模板的HTML邮件发送失败: {}, 主题: {}, 模板: {}", to, subject, template, e);
      throw new RuntimeException("邮件发送失败，请稍后重试");
    }
  }

  /**
   * 通过 Thymeleaf 模板群发 HTML 邮件
   *
   * @param toList 收件人邮箱列表
   * @param subject 邮件主题
   * @param template 模板路径（相对于 resources/templates，后缀不带 .html）
   * @param variables 模板变量Map
   */
  public void sendHtmlMailWithTemplate(List<String> toList,
                                       String subject,
                                       String template,
                                       Map<String, Object> variables) {
    try {
      Context context = new Context();
      context.setVariables(variables);
      String htmlContent = templateEngine.process(template, context);

      sendHtmlMail(toList, subject, htmlContent);
    } catch (Exception e) {
      log.error("基于模板的HTML群发邮件发送失败: {}, 主题: {}, 模板: {}",
                toList,
                subject,
                template,
                e);
      throw new RuntimeException("邮件群发失败，请稍后重试");
    }
  }
}
