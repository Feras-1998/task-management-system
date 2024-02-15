package com.feras.task.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    private static final String DEV_URL = "http://localhost:8090";
    private static final String PROD_URL = "http://localhost:8090";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(DEV_URL);
        devServer.setDescription("Local Server");

        Server prodServer = new Server();
        prodServer.setUrl(PROD_URL);
        prodServer.setDescription("Prod Server");

        Info info = new Info()
                .title("Task Management")
                .version("1.0")
                .description("This API exposes endpoints Task Management Project.");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}