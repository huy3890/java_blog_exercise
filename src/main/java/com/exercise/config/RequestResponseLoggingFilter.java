package com.exercise.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.exercise.controller.PostsController;

@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

  public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

  @Override
  public void doFilter(ServletRequest request, javax.servlet.ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;
    HttpSession httpSession = req.getSession(true);
    httpSession.removeAttribute(NOTIFY_MSG_SESSION_KEY);
    logger.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
    chain.doFilter(request, response);
    logger.info("Logging Response :{}", res.getContentType());

  }

}
