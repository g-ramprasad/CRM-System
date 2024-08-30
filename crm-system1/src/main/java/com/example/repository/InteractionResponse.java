package com.example.repository;

import java.util.List;

import com.example.model.Interaction;

public class InteractionResponse {
    
    private List<Row> rows;
    
    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row {
        private Interaction value;
        
        public Interaction getValue() {
            return value;
        }

        public void setValue(Interaction value) {
            this.value = value;
        }
    }
}
