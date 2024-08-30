package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer("cus101", "Saurabh", "saurabh@example.com", "1234567890", "123 Main St, Anytown, USA");
    }

    @Test
    public void testAddCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer savedCustomer = customerService.addCustomer(customer);
    }

    @Test
    public void testGetCustomer() {
        // Mocking findById to return an Optional of the customer
        when(customerRepository.findById("cus101")).thenReturn(customer);
        Customer foundCustomer = customerService.getCustomer("cus101");
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer updatedCustomer = customerService.updateCustomer(customer);
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById("cus101");
        customerService.deleteCustomer("cus101");
        verify(customerRepository, times(1)).deleteById("cus101");
    }
}
