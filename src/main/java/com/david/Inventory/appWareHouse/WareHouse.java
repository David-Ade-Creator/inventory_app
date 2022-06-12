package com.david.Inventory.appWareHouse;

import com.david.Inventory.appInventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    @Column(unique=true)
    private String registrationId;
    private String location;
    @ManyToMany
    @JoinTable(
            name = "inventory_collection",
            joinColumns = @JoinColumn(name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    Set<Inventory> inventory_collection = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Inventory> getInventory_collection() {
        return inventory_collection;
    }

    public void setInventory_collection(Set<Inventory> inventory_collection) {
        this.inventory_collection = inventory_collection;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", location='" + location + '\'' +
                ", inventory_collection=" + inventory_collection +
                '}';
    }
}
