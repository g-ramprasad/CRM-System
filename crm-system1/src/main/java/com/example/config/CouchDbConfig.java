package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.example.client.CouchDbClient;

@Configuration
public class CouchDbConfig {

    @Value("${couchdb.url}")
    private String couchDbUrl;

    @Value("${couchdb.database}")
    private String couchDbDatabase;
    
    @Bean
    public CouchDbClient couchDbClient(RestTemplate restTemplate) {
        return new CouchDbClient(couchDbUrl, couchDbDatabase, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
