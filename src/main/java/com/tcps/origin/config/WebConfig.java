package com.tcps.origin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

//    @Autowired
//    private AuthenticateInterceptor authenticateInterceptor;

//    @Bean
//    public FilterRegistrationBean loginFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        LoginFilter loginFilter = new LoginFilter();
//        filterRegistrationBean.setFilter(loginFilter);
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        filterRegistrationBean.setUrlPatterns(urls);
//        return filterRegistrationBean;
//    }

//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(authenticateInterceptor);
//        addInterceptor.addPathPatterns("/**");
//        addInterceptor.excludePathPatterns("/auth**");
//    }
}
