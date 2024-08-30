package com.example.repository;

import java.util.List;

import com.example.model.Sales;

public class SalesResponse {
    
    private List<Row> rows;
    
    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row {
        private Sales value;
        
        public Sales getValue() {
            return value;
        }

        public void setValue(Sales value) {
            this.value = value;
        }
    }
}
