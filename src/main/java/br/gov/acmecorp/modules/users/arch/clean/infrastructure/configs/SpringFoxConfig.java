package br.gov.acmecorp.modules.users.arch.clean.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringFoxConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("REST API do Cliente: SEEC - Secretaria de Estado da Economia e Comércio")
                .description("Projeto para atender ao desafio técnico na(s) empresa(s) {{NOME_EMPRESA}} para o cliente [{{NOME_CLIENTE}}](LINK_CLIENTE)")
                .version("v1.0")
                .contact(new Contact()
                        .name("Paulo Sérgio da Silva")
                        .url("https://github.com/pssilva?tab=repositories")
                        .email("pss1suporte@gmail.com"))
                .termsOfService("TOC")
                .license(new License().name("License").url("#"))
            );
    }

}
