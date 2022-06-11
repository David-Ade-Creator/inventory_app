package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping
    List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }


    @PostMapping
    Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
