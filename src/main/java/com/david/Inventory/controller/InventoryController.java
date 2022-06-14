package com.david.Inventory.controller;

import com.david.Inventory.service.InventoryService;
import com.david.Inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<List<Inventory>> getInventories() {
        return inventoryService.inventoryList();
    }

    @PostMapping
    ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("{inventoryId}")
    ResponseEntity<Inventory> updateInventory(@Valid @PathVariable Long inventoryId, @RequestBody Inventory inventory){
        return inventoryService.editInventory(inventoryId, inventory);
    }

    @DeleteMapping("{inventoryId}")
    ResponseEntity<HttpStatus> deleteInventory(@PathVariable Long inventoryId){
        return inventoryService.deleteInventory(inventoryId);
    }
}
