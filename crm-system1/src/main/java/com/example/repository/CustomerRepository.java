package com.example.repository;

import com.example.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

@Repository
public interface CustomerRepository {
    Customer findById(String id);
    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);
    Customer save(Customer customer);
    void deleteById(String id);
	List<Customer> findAll();
}
