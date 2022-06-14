package com.david.Inventory.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 25, message = "Name must be between 5 and 25 characters long")
    @Column(name="warehouse_name", length=25, nullable=false)
    private String name;

    @NotEmpty(message = "Registration id cannot be empty")
    @Size(min = 5, max = 15, message = "Warehouse registration id must be between 5 and 15 characters long")
    @Column(name="warehouse_reg_id",length=15, nullable=false, unique=true)
    private String registrationId;

    @NotEmpty(message = "Location cannot be empty")
    @Size(min = 5, max = 20, message = "Location must be between 5 and 20 characters long")
    @Column(name="warehouse_location", length=20, nullable=false)
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
