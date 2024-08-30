package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Customer;
import com.example.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Returns a list of all customers")
    @ApiResponse(responseCode = "200", description = "Customers found")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Create a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a customer by ID", description = "Returns a single customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found with the given ID"),
            @ApiResponse(responseCode = "404", description = "Customer not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<String> getCustomer(@PathVariable String id) {
        Customer customer = customerService.getCustomer(id);
        if (customer != null) {
            return ResponseEntity.ok("Customer with ID " + id + " found");
        } else {
        	return new ResponseEntity<String>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("view/name/{name}")
    public ResponseEntity<String> getCustomerByName(@PathVariable String name) {
        Customer customer = customerService.getCustomerByName(name);
        if (customer != null) {
            return ResponseEntity.ok("Customer with name " + name + " found");
        } else {
        	return new ResponseEntity<String>("Customer with ID " + name + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update customer details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        if (customerService.getCustomer(id) != null) {
            customer.setId(id); // Ensure the ID is set correctly before updating
            Customer updatedCustomer = customerService.updateCustomer(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
        	return new ResponseEntity<String>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	return new ResponseEntity<String>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
