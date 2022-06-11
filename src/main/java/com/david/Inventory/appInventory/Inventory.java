package com.david.Inventory.appInventory;


import com.david.Inventory.appWareHouse.WareHouse;

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
    @ManyToMany
    @JoinTable(
            name = "warehouse_allocated",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    Set<WareHouse> wareHouses = new HashSet<>();
}
