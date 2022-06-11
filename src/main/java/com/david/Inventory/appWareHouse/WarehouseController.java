package com.david.Inventory.appWareHouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping
    List<WareHouse> getWarehouses() {
        return warehouseRepository.findAll();
    }


    @PostMapping
    WareHouse createWarehouse(@RequestBody WareHouse wareHouse) {
        return warehouseRepository.save(wareHouse);
    }

//     TODO
//    @PutMapping("/{wareHouseId}/Inventory/{inventoryId}")
//    WareHouse addInventoryToWarehouse(
//            @PathVariable Long inventoryId,
//            @PathVariable Long warehouseId
//    ) {}


//    TODO
//    @DeleteMapping


}
