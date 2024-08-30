package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.model.Customer;

@Service
public class CouchDbService {

    @Value("${couchdb.url}")
    private String couchDbUrl;

    @Value("${couchdb.database}")
    private String couchDbDatabase;

    private final RestTemplate restTemplate;

    public CouchDbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getDocument(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(couchDbUrl)
                .pathSegment(couchDbDatabase, id)
                .toUriString();
        return restTemplate.getForObject(url, Customer.class);
    }

    public void createDocument(Customer customer) {
        String url = UriComponentsBuilder.fromHttpUrl(couchDbUrl)
                .pathSegment(couchDbDatabase)
                .toUriString();
        restTemplate.postForEntity(url, customer, String.class);
    }

    public void updateDocument(String id, Customer customer) {
        String url = UriComponentsBuilder.fromHttpUrl(couchDbUrl)
                .pathSegment(couchDbDatabase, id)
                .toUriString();
        restTemplate.put(url, customer);
    }

    public void deleteDocument(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(couchDbUrl)
                .pathSegment(couchDbDatabase, id)
                .toUriString();
        restTemplate.delete(url);
    }

    public ResponseEntity<String> getAllDocuments() {
        String url = UriComponentsBuilder.fromHttpUrl(couchDbUrl)
                .pathSegment(couchDbDatabase, "_all_docs")
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }
}
