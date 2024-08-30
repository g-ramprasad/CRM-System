package com.example.service;

import com.example.model.Sales;
import com.example.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> getAllSales() {
        return (List<Sales>) salesRepository.findAll();
    }

    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales getSales(String id) {
        return salesRepository.findById(id).orElse(null);
    }

    public Sales updateSales(String id, Sales sales) {
        return salesRepository.save(sales);
    }

    public void deleteSales(String id) {
        salesRepository.deleteById(id);
    }
}
