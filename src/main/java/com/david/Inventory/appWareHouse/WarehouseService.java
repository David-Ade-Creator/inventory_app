package com.david.Inventory.appWareHouse;

import com.david.Inventory.appInventory.Inventory;
import com.david.Inventory.appInventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<WareHouse> getWarehouseList(){
        return warehouseRepository.findAll();
    }

    public WareHouse createWarehouse(WareHouse wareHouse){
        return warehouseRepository.save(wareHouse);
    }

    @Transactional
    public WareHouse editWarehouse(Long id, WareHouse wareHouse){
        WareHouse updateWarehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("warehouse with id " + id + " does not exist"));
        updateWarehouse.setName(wareHouse.getName());
        updateWarehouse.setLocation(wareHouse.getLocation());
        updateWarehouse.setRegistrationId(wareHouse.getRegistrationId());
        return warehouseRepository.save(updateWarehouse);
    }

    @Transactional
    public WareHouse addInventoryToWarehouse(Long warehouseId,Long inventoryId){
        WareHouse warehouse = warehouseRepository.findById(warehouseId).get();
        Inventory inventory = inventoryRepository.findById(inventoryId).get();
        warehouse.inventory_collection.add(inventory);
        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public void deleteWarehouse(Long id){
        WareHouse wareHouse = warehouseRepository.findById(id).get();
        wareHouse.getInventory_collection().forEach(inv -> inv.getWarehouses().remove(wareHouse));
        inventoryRepository.saveAll(wareHouse.getInventory_collection());
        warehouseRepository.delete(wareHouse);
    }

    @Transactional
    public void deleteWarehouseInventory(Long warehouseId,Long inventoryId){
        WareHouse warehouse = warehouseRepository.findById(warehouseId).get();
        Inventory inventory = inventoryRepository.findById(inventoryId).get();
        warehouse.getInventory_collection().remove(inventory);
        inventory.getWarehouses().remove(warehouse);
        warehouseRepository.save(warehouse);
        inventoryRepository.save(inventory);
    }
}
