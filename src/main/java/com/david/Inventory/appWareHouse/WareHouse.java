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
    private String registrationId;
    private String location;
    @JsonIgnore
    @ManyToMany(mappedBy = "wareHouses")
    private Set<Inventory> inventories = new HashSet<>();

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

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", location='" + location + '\'' +
                ", inventories=" + inventories +
                '}';
    }
}
