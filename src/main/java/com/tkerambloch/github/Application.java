package com.tkerambloch.github;

import com.tkerambloch.github.config.testAppProperties;
import com.tkerambloch.github.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tkerambloch on 06/01/16.
 */
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties({testAppProperties.class})
public class Application extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /*
     * Main method, entry point of the app if launch with the command line
     */
    public static void main(String[] argv) {
        SpringApplication app = new SpringApplication(Application.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(argv);
        addDefaultProfile(app, source);
        app.run(argv);
    }

    /*
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") &&
                !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles(Constants.PROFILE_DEVELOPMENT);
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }
}
