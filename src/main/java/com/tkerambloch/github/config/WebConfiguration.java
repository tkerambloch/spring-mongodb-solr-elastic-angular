package com.tkerambloch.github.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;

/**
 * Created by tkerambloch on 07/01/16.
 */
@Configuration
public class WebConfiguration implements ServletContextInitializer, EmbeddedServletContainerCustomizer, WebApplicationInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    @Inject
    private Environment env;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
    }

}
