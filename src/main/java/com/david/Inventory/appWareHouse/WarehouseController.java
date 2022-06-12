package com.david.Inventory.appWareHouse;

import com.david.Inventory.appInventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    List<WareHouse> getWarehouses() {
        return warehouseService.getWarehouseList();
    }


    @PostMapping
    ResponseEntity createWarehouse(@Valid @RequestBody WareHouse wareHouse) {
        return warehouseService.createWarehouse(wareHouse);
    }

    @PutMapping("/{warehouseId}")
    WareHouse editWarehouse(@Valid @RequestBody WareHouse wareHouse, @PathVariable Long warehouseId){
        return warehouseService.editWarehouse(warehouseId, wareHouse);
    }

    @PutMapping("/{warehouseId}/inventory/{inventoryId}")
    WareHouse addInventoryToWarehouse(
            @PathVariable Long warehouseId,
            @PathVariable Long inventoryId
    ) {
        return warehouseService.addInventoryToWarehouse(warehouseId,inventoryId);
    }


    @DeleteMapping("/{warehouseId}")
    void deleteWareHouse(@PathVariable Long warehouseId){
        warehouseService.deleteWarehouse(warehouseId);
    }

    @DeleteMapping("/{warehouseId}/inventory/{inventoryId}")
    void deleteInventoryInWarehouse(
            @PathVariable Long warehouseId,
            @PathVariable Long inventoryId
    ) {
        warehouseService.deleteWarehouseInventory(warehouseId, inventoryId);
    }


}
