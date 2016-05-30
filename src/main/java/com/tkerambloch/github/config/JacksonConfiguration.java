package com.tkerambloch.github.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Created by tkerambloch on 06/01/16.
 */
@Configuration
public class JacksonConfiguration {


    /*
     * Custom Jackson object mapper
     */
    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        return new Jackson2ObjectMapperBuilder()
                .findModulesViaServiceLoader(true)
                .modulesToInstall(javaTimeModule);
    }
}
