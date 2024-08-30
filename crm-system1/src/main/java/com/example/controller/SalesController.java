package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.model.Sales;
import com.example.service.SalesService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sales> addSales(@RequestBody Sales sales) {
        Sales createdSales = salesService.addSales(sales);
        return new ResponseEntity<>(createdSales, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSales(@PathVariable String id) {
        Sales sales = salesService.getSales(id);
        if (sales != null) {
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sales record with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSales(@PathVariable String id, @RequestBody Sales sales) {
        if (salesService.getSales(id) != null) {
            Sales updatedSales = salesService.updateSales(id, sales);
     return new ResponseEntity<>(updatedSales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Sales record with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSales(@PathVariable String id) {
        if (salesService.getSales(id) != null) {
            salesService.deleteSales(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Cannot delete. Sales record with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
