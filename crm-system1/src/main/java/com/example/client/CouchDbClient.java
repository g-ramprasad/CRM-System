package com.example.client;

import org.springframework.web.client.RestTemplate;

public class CouchDbClient {

    private final String couchDbUrl;
    private final String couchDbDatabase;
    private final RestTemplate restTemplate;

    public CouchDbClient(String couchDbUrl, String couchDbDatabase, RestTemplate restTemplate) {
        this.couchDbUrl = couchDbUrl;
        this.couchDbDatabase = couchDbDatabase;
        this.restTemplate = restTemplate;
    }

    public String getAllDocs() {
        String url = String.format("%s/%s/_all_docs", couchDbUrl, couchDbDatabase);
        return restTemplate.getForObject(url, String.class);
    }

}
