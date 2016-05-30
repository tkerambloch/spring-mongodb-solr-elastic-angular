package com.tkerambloch.github.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Created by tkerambloch on 04/03/2016.
 */
@Configuration
@EnableSolrRepositories(value = "com.tkeramloch.github.repository.solr", multicoreSupport = true)
public class SolrConfiguration implements EnvironmentAware{

    private RelaxedPropertyResolver propertyResolver;
    private Environment             environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.data.solr.");
    }

    @Bean
    public SolrServer solrServer() {
        String solrHost = propertyResolver.getProperty("host");
        return new HttpSolrServer(solrHost);
    }

    @Bean(name = "establishmentSolrTemplate")
    public SolrOperations establishmentSolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorEstablishment");
    }

    @Bean(name = "dealerSolrTemplate")
    public SolrOperations dealerSolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorDealer");
    }

    @Bean(name = "countrySolrTemplate")
    public SolrOperations countrySolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorCountry");
    }

    @Bean(name = "regionSolrTemplate")
    public SolrOperations regionSolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorRegion");
    }

    @Bean(name = "citySolrTemplate")
    public SolrOperations citySolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorCity");
    }

    @Bean(name = "districtSolrTemplate")
    public SolrOperations districtSolrTemplate() {
        HttpSolrServer httpSolrServer = new HttpSolrServer(propertyResolver.getProperty("host"));
        return new SolrTemplate(httpSolrServer, "StoreLocatorDistrict");
    }

}
