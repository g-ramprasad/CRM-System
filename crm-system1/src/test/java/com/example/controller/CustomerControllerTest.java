package com.example.controller;

import com.example.model.Customer;
import com.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setUp() {
        customer1 = new Customer("cus101", "Saurabh", "saurabh@example.com", "1234567890", "123 Main St, Anytown, USA");
        customer2 = new Customer("cus102", "Jane Smith", "jane.smith@example.com", "0987654321", "456 Maple Ave, Anytown, USA");
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON));    }

    @Test
    public void testAddCustomer() throws Exception {
        when(customerService.addCustomer(any(Customer.class))).thenReturn(customer1);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"cus101\",\"name\":\"Saurabh\",\"email\":\"saurabh@example.com\",\"phone\":\"1234567890\",\"address\":\"123 Main St, Anytown, USA\"}"));    }

    @Test
    public void testGetCustomerById() throws Exception {
        when(customerService.getCustomer("cus101")).thenReturn(customer1);

        mockMvc.perform(get("/customers/cus101")
                .contentType(MediaType.APPLICATION_JSON));    }

    @Test
    public void testUpdateCustomer() throws Exception {
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(customer1);

        mockMvc.perform(put("/customers/cus101")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"cus101\",\"name\":\"Saurabh\",\"email\":\"saurabh@example.com\",\"phone\":\"1234567890\",\"address\":\"123 Main St, Anytown, USA\"}"));    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/cus101")
                .contentType(MediaType.APPLICATION_JSON));
    }
}
