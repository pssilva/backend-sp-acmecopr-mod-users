package br.gov.acmecorp.modules.users.arch.clean.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${access.control.allow.origin.url}")
    private String accessCAOUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
       .allowedOrigins(accessCAOUrl)
       .allowedMethods(
           HttpMethod.HEAD.name(),
           HttpMethod.PUT.name(),
           HttpMethod.POST.name(),
           HttpMethod.DELETE.name(),
           HttpMethod.PATCH.name(),
           HttpMethod.OPTIONS.name())
        .allowedHeaders(
            HttpHeaders.ORIGIN,
            HttpHeaders.ACCEPT,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
            HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
            HttpHeaders.AUTHORIZATION
        );
    }
}
