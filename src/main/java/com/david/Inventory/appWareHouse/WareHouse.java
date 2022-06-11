package com.david.Inventory.appWareHouse;

import com.david.Inventory.appInventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
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

}
