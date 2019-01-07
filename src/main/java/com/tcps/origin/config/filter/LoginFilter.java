package com.tcps.origin.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String host = request.getRemoteHost();
        String remotHost = request.getRemoteHost();
        String remotAddr = request.getRemoteAddr();
        String contentType = request.getContentType();
        log.info("host:{}", host);
        log.info("remotHost:{}", remotHost);
        log.info("remotAddr:{}", remotAddr);
        log.info("contentType:{}", contentType);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
