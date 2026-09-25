package com.vlad.project.config;

import com.vlad.project.client.HttpDealClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.vlad.project.client",
        types = HttpDealClient.class)
public class HttpDealClientConfig {
}
