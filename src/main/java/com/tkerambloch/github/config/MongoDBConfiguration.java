package com.tkerambloch.github.config;

import com.mongodb.Mongo;
import org.mongeez.Mongeez;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkerambloch on 19/02/2016.
 */
@Configuration
@EnableMongoRepositories(value = "com.tkerambloch.github.repository.mongodb")
@Import(value = MongoAutoConfiguration.class)
@EnableTransactionManagement
public class MongoDBConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;
    private Environment             environment;

    @Inject
    private Mongo mongo;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.data.mongodb.");
    }
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo, propertyResolver.getProperty("databaseName"));
    }

    public static class NotDevCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return !context.getEnvironment().acceptsProfiles(Constants.PROFILE_DEVELOPMENT) && !context.getEnvironment().acceptsProfiles("test");
        }
    }

    public static class NProdCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return context.getEnvironment().acceptsProfiles(Constants.PROFILE_NPROD) ;
        }
    }

    public static class ProdCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return context.getEnvironment().acceptsProfiles(Constants.PROFILE_PROD) ;
        }
    }

    @Bean(name = {"org.springframework.boot.autoconfigure.AutoConfigurationUtils.basePackages"})
    public List<String> getBasePackages() {
        List<String> basePackages = new ArrayList<>();
        basePackages.add("com.tkerambloch.github.domain.mongodb");
        return basePackages;
    }

    @Bean
    public Mongeez mongeez() {
        String masterFile = environment.getProperty("mongeez.masterFile");
        Mongeez mongeez = new Mongeez();

        mongeez.setFile(new ClassPathResource(masterFile));
        mongeez.setMongo(mongo);
        mongeez.setDbName(propertyResolver.getProperty("databaseName"));
        mongeez.process();

        return mongeez;
    }
}
