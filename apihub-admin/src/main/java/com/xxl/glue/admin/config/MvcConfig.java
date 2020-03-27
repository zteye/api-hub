package com.xxl.glue.admin.config;

import com.xxl.glue.admin.controller.interceptor.CookieInterceptor;
import com.xxl.glue.admin.controller.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源； *.css,*.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/static/**","/webjars/**");
                // /**  表示拦截所有路径下的所有请求
                //1.加入的顺序就是拦截器执行的顺序，
                //2.按顺序执行所有拦截器的preHandle
                //3.所有的preHandle 执行完再执行全部postHandle 最后是postHandle
                registry.addInterceptor(new PermissionInterceptor())
                        .addPathPatterns("/**");
                registry.addInterceptor(new CookieInterceptor()).addPathPatterns("/**");

            }
        };
    }
}
