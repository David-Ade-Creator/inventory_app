package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import com.david.Inventory.appWareHouse.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Inventory> inventoryList(){
        return inventoryRepository.findAll();
    }

    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public Inventory editInventory(Inventory inventory){
        Inventory updateInventory = inventoryRepository.findById(inventory.getId())
                .orElseThrow(()-> new IllegalStateException("inventory with id " + inventory.getId() + " does not exist"));
        updateInventory.setName(inventory.getName());
        updateInventory.setQuantity(inventory.getQuantity());
        updateInventory.setDescription(inventory.getDescription());
        updateInventory.setType(inventory.getType());
        return inventoryRepository.save(updateInventory);
    }

    @Transactional
    public Inventory deleteInventory(Long id){
        Inventory inventory = inventoryRepository.findById(id).get();
        inventory.getWarehouses().forEach(warehouse->warehouse.getInventory_collection().remove(inventory));
        warehouseRepository.saveAll(inventory.getWarehouses());
        inventoryRepository.delete(inventory);
        return inventory;
    }

}
