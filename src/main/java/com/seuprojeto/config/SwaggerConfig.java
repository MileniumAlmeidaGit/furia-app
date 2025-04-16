package com.seuprojeto.config;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto FURIA API")
                        .version("1.0")
                        .description("API para gerenciamento de usuarios, notificacoes,chatbot e integrações com redes sociais."));

    }
}
