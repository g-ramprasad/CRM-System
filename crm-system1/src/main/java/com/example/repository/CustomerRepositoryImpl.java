package com.example.repository;

import com.example.model.Customer;
import com.example.model.Interaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	

    private final RestTemplate restTemplate;
    private final String couchDbUrl;

    public CustomerRepositoryImpl(RestTemplate restTemplate, @Value("${couchdb.url}") String couchDbUrl) {
        this.restTemplate = restTemplate;
        this.couchDbUrl = couchDbUrl;
    }

    public Customer findById(String id) {
        return restTemplate.getForObject(couchDbUrl + "/customers/" + id, Customer.class);
    }

    public List<Customer> findByName(String name) {
        String url = couchDbUrl + "/customers/_design/customer/_view/by_name?key=\"" + name + "\"";
        CustomerResponse response = restTemplate.getForObject(url, CustomerResponse.class);
        return response.getRows().stream().map(CustomerResponse.Row::getValue).collect(Collectors.toList());
    }
    
    public List<Interaction> findByInteractionType(String interactionType) {
        String url = couchDbUrl + "/interactions/_design/interaction/_view/by_type?key=\"" + interactionType + "\"";
        InteractionResponse response = restTemplate.getForObject(url, InteractionResponse.class);
        return response.getRows().stream().map(InteractionResponse.Row::getValue).collect(Collectors.toList());
    }


    public List<Customer> findByEmail(String email) {
        String url = couchDbUrl + "/customers/_design/customer/_view/by_email?key=\"" + email + "\"";
        CustomerResponse response = restTemplate.getForObject(url, CustomerResponse.class);
        return response.getRows().stream().map(CustomerResponse.Row::getValue).collect(Collectors.toList());
    }

    public Customer save(Customer customer) {
        return restTemplate.postForObject(couchDbUrl + "/customers", customer, Customer.class);
    }

    public void deleteById(String id) {
        restTemplate.delete(couchDbUrl + "/customers/" + id);
    }

	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
