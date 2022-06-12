package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    List<Inventory> getInventories() {
        return inventoryService.inventoryList();
    }


    @PostMapping
    Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping
    Inventory updateInventory(@RequestBody Inventory inventory){
        return inventoryService.editInventory(inventory);
    }

    @DeleteMapping("{inventoryId}")
    Inventory deleteInventory(@PathVariable Long inventoryId){
        return inventoryService.deleteInventory(inventoryId);
    }
}
