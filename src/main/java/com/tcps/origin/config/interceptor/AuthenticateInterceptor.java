package com.tcps.origin.config.interceptor;

import ch.qos.logback.core.net.server.Client;
import com.tcps.origin.appcore.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Enumeration;

public class AuthenticateInterceptor{}

//此处@Component与filter中@Component注解不同，此处简单注入Spring容器，不拦截url。
//@Slf4j
//@Component
//public class AuthenticateInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private ClientService clientService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) {
////        HandlerMethod handlerMethod = (HandlerMethod) handler;
////        String className = handlerMethod.getBean().getClass().getName();
////        String methodName = handlerMethod.getMethod().getName();
////        request.setAttribute("starttime", new Date().getTime());
//        // 展示所有头部信息。
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            log.info("headerName:{} -- value:{}", headerName, request.getHeader(headerName));
//        }
////        boolean clientSecretIsOk = authClient(request);
//        boolean userpasswordIsOk = authUser(request);
//        return true;// 只有return true 才能继续向下执行->Controller。
//    }
//
//    // 如果抛出异常，跳过此法。
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response,
//                           Object handler,
//                           ModelAndView modelAndView) {
////        Long starttime = (Long) request.getAttribute("starttime");
////        log.info("耗时:{}", (new Date().getTime() - starttime));
//    }
//
//    // 如果抛出异常，直接到此法。
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response,
//                                Object handler,
//                                Exception ex) {
//
//    }
//
//    // 校验客户端
//    private boolean authClient(HttpServletRequest request) {
//        String authorization = request.getHeader("authorization");
//        if (StringUtils.isEmpty(authorization)) {
//            throw new AuthException("authorization 是空的！" + authorization);
//        }
//        String authorizations[] = authorization.split(" ");
//        if (authorizations.length != 2) {
//            throw new AuthException("authorization 格式错误！" + authorization);
//        }
//        String base64String = authorizations[1];
//        String accountString = new String(Base64.decodeBase64(base64String), Charset.forName("UTF-8"));
//        String account[] = accountString.split(":");
//        if (account.length != 2) {
//            throw new AuthException("未收到ClientId或Password！" + accountString);
//        }
//        String clientId = account[0];
//        String clientSecrt = account[1];
//        Client client = clientService.findClientByClientId(clientId);
//        return BCrypt.checkpw(clientSecrt, client.getClientPassword());
//    }
//
//    // 校验用户
//    private boolean authUser(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info("username:{}", username);
//        log.info("password:{}", password);
//        return true;
//    }
//}
