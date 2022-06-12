package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByName(String name);
}
