package com.david.Inventory.service;

import com.david.Inventory.model.Inventory;
import com.david.Inventory.repository.InventoryRepository;
import com.david.Inventory.exception.AlreadyExistException;
import com.david.Inventory.exception.DataNotFoundException;
import com.david.Inventory.model.WareHouse;
import com.david.Inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public ResponseEntity<List<WareHouse>> getWarehouseList(){
        try {

            return new ResponseEntity<>(warehouseRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WareHouse> createWarehouse(WareHouse wareHouse){
        try {
            WareHouse existingWarehouse = warehouseRepository.findByRegistrationId(wareHouse.getRegistrationId());
            if(existingWarehouse != null){
                throw new AlreadyExistException("Warehouse with registration id" + wareHouse.getRegistrationId() + " exists");
            }
            WareHouse savedWarehouse =  warehouseRepository.save(wareHouse);
            return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public  ResponseEntity<WareHouse> editWarehouse(Long id, WareHouse wareHouse){
        try {
            WareHouse updateWarehouse = warehouseRepository.findById(id)
                    .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + id + "not found"));
            updateWarehouse.setName(wareHouse.getName());
            updateWarehouse.setLocation(wareHouse.getLocation());
            updateWarehouse.setRegistrationId(wareHouse.getRegistrationId());
            return new ResponseEntity<>(warehouseRepository.save(updateWarehouse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<WareHouse> addInventoryToWarehouse(Long warehouseId,Long inventoryId){
        try {
            WareHouse warehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + warehouseId + "not found"));
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(()-> new DataNotFoundException("Inventory with id" + inventoryId + "not found"));
            Set<Inventory> _inventory = warehouse.getInventory_collection();
            _inventory.add(inventory);
            warehouse.setInventory_collection(_inventory);
            WareHouse _warehouse = warehouseRepository.save(warehouse);
            return new ResponseEntity<>(_warehouse, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteWarehouse(Long id){
        try {
            WareHouse wareHouse = warehouseRepository.findById(id)
                    .orElseThrow(()-> new DataNotFoundException("Warehouse with id " + id + " not found"));
            wareHouse.getInventory_collection().forEach(inv -> inv.getWarehouses().remove(wareHouse));
            inventoryRepository.saveAll(wareHouse.getInventory_collection());
            warehouseRepository.delete(wareHouse);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteWarehouseInventory(Long warehouseId,Long inventoryId){
        try {
            WareHouse warehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + warehouseId + "not found"));
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(()-> new DataNotFoundException("Inventory with id" + inventoryId + "not found"));
            warehouse.getInventory_collection().remove(inventory);
            inventory.getWarehouses().remove(warehouse);
            warehouseRepository.save(warehouse);
            inventoryRepository.save(inventory);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
