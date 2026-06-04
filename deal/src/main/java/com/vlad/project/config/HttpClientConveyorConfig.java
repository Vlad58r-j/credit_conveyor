package com.vlad.project.config;

import com.vlad.project.client.HttpConveyorClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.vlad.project.client",
        types = HttpConveyorClient.class)
public class HttpClientConveyorConfig {
}