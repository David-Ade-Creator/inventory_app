package com.david.Inventory.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Column(name="inventory_name", length = 32,nullable = false, unique = true)
    private String name;
    @Min(0)
    @Max(50)
    @Column(name="inventory_qty", nullable = false)
    private Integer quantity;
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, max = 64, message = "Description must be between 5 and 64 characters long")
    @Column(name="inventory_desc", length = 64,nullable = false)
    private String description;
    @NotNull(message = "Type cannot be empty")
    @Enumerated(EnumType.STRING)
    private InventoryType type;
    @JsonIgnore
    @ManyToMany(mappedBy = "inventory_collection")
    private Set<WareHouse> warehouses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InventoryType getType() {
        return type;
    }

    public void setType(InventoryType type) {
        this.type = type;
    }

    public Set<WareHouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<WareHouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", warehouses=" + warehouses +
                '}';
    }
}
