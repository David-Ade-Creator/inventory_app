package com.david.Inventory.service;

import com.david.Inventory.repository.WarehouseRepository;
import com.david.Inventory.exception.AlreadyExistException;
import com.david.Inventory.exception.DataNotFoundException;
import com.david.Inventory.model.Inventory;
import com.david.Inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public ResponseEntity<List<Inventory>> inventoryList(){
        try {

            return new ResponseEntity<>(inventoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Inventory> saveInventory(Inventory inventory){
        try {
            Inventory existingInventory = inventoryRepository.findByName(inventory.getName());
            if(existingInventory != null){
                throw new AlreadyExistException("Inventory with name " + inventory.getName() + " exists");
            }
            Inventory savedInventory = inventoryRepository.save(inventory);
            return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Inventory> editInventory(Long inventoryId,Inventory inventory){
        try {
            Inventory updateInventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(()-> new DataNotFoundException("inventory with id " + inventoryId + " does not exist"));
            updateInventory.setName(inventory.getName());
            updateInventory.setQuantity(inventory.getQuantity());
            updateInventory.setDescription(inventory.getDescription());
            updateInventory.setType(inventory.getType());
            return new ResponseEntity<>(inventoryRepository.save(updateInventory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteInventory(Long id){
        try {
            Inventory inventory = inventoryRepository.findById(id)
                    .orElseThrow(()-> new DataNotFoundException("inventory with id " + id + " does not exist"));
            inventory.getWarehouses().forEach(warehouse->warehouse.getInventory_collection().remove(inventory));
            warehouseRepository.saveAll(inventory.getWarehouses());
            inventoryRepository.delete(inventory);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
