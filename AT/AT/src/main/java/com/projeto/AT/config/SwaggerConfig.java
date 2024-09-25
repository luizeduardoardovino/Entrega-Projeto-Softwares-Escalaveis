package com.projeto.AT.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Livros 📚")
                        .version("1.0")
                        .description("Documentação da API para gerenciar autores, usuários e livros. Aqui você pode visualizar e testar os endpoints disponíveis.")
                        .termsOfService("http://seuprojeto.com/terms"));
                       
    }
}
