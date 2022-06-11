package com.david.Inventory.appInventory;


import com.david.Inventory.appWareHouse.WareHouse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private Integer quantity;
    private String description;
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
