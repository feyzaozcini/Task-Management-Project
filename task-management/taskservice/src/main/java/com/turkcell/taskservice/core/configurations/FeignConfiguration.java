package com.turkcell.taskservice.core.configurations;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@RequiredArgsConstructor
public class FeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes !=null){
            String token = attributes.getRequest().getHeader("Authorization");
            if(token != null && token.startsWith("Bearer")){
                token = token.substring(7);
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        }
    }
}