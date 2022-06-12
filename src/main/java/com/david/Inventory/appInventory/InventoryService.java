package com.david.Inventory.appInventory;

import com.david.Inventory.appWareHouse.WareHouse;
import com.david.Inventory.appWareHouse.WarehouseRepository;
import com.david.Inventory.exception.AlreadyExistException;
import com.david.Inventory.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    public ResponseEntity saveInventory(Inventory inventory){
        Inventory existingInventory = inventoryRepository.findByName(inventory.getName());

        if(existingInventory != null){
            throw new AlreadyExistException("Inventory with name " + inventory.getName() + " exists");
        }

        Inventory savedInventory = inventoryRepository.save(inventory);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedInventory.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    public Inventory editInventory(Long inventoryId,Inventory inventory){
        Inventory updateInventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new DataNotFoundException("inventory with id " + inventoryId + " does not exist"));
        updateInventory.setName(inventory.getName());
        updateInventory.setQuantity(inventory.getQuantity());
        updateInventory.setDescription(inventory.getDescription());
        updateInventory.setType(inventory.getType());
        return inventoryRepository.save(updateInventory);
    }

    @Transactional
    public Inventory deleteInventory(Long id){
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("inventory with id " + id + " does not exist"));
        inventory.getWarehouses().forEach(warehouse->warehouse.getInventory_collection().remove(inventory));
        warehouseRepository.saveAll(inventory.getWarehouses());
        inventoryRepository.delete(inventory);
        return inventory;
    }

}
