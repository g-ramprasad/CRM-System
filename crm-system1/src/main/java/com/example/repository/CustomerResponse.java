package com.example.repository;

import com.example.model.Customer;
import java.util.List;

public class CustomerResponse {
    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row {
        private Customer value;

        public Customer getValue() {
            return value;
        }

        public void setValue(Customer value) {
            this.value = value;
        }
    }
}
