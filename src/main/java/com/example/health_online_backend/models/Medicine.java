package com.example.health_online_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Medicine")
public class Medicine {
    @Id
    private String id;
    private String name;
    private String unit;
    private String description;
    private int quantity;

    public Medicine() {
    }

    public Medicine(String id, String name, String unit, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.quantity = quantity;
    }

    public Medicine(String name, String unit, String description, int quantity) {
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
