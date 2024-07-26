package com.turkcell.gatewayserver.core;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;
public class GatewayConfiguration {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}