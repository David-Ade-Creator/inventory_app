package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    ResponseEntity createInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("{inventoryId}")
    Inventory updateInventory(@Valid @PathVariable Long inventoryId, @RequestBody Inventory inventory){
        return inventoryService.editInventory(inventoryId, inventory);
    }

    @DeleteMapping("{inventoryId}")
    Inventory deleteInventory(@PathVariable Long inventoryId){
        return inventoryService.deleteInventory(inventoryId);
    }
}
