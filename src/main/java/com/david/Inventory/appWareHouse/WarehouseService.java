package com.david.Inventory.appWareHouse;

import com.david.Inventory.appInventory.Inventory;
import com.david.Inventory.appInventory.InventoryRepository;
import com.david.Inventory.exception.AlreadyExistException;
import com.david.Inventory.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
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

    public ResponseEntity createWarehouse(WareHouse wareHouse){

        WareHouse existingWarehouse = warehouseRepository.findByRegistrationId(wareHouse.getRegistrationId());

        if(existingWarehouse != null){
            throw new AlreadyExistException("Warehouse with registration id" + wareHouse.getRegistrationId() + " exists");
        }

        WareHouse savedWarehouse =  warehouseRepository.save(wareHouse);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWarehouse.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    public WareHouse editWarehouse(Long id, WareHouse wareHouse){
        WareHouse updateWarehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + id + "not found"));
        updateWarehouse.setName(wareHouse.getName());
        updateWarehouse.setLocation(wareHouse.getLocation());
        updateWarehouse.setRegistrationId(wareHouse.getRegistrationId());
        return warehouseRepository.save(updateWarehouse);
    }

    @Transactional
    public WareHouse addInventoryToWarehouse(Long warehouseId,Long inventoryId){
        WareHouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + warehouseId + "not found"));
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new DataNotFoundException("Inventory with id" + inventoryId + "not found"));
        warehouse.inventory_collection.add(inventory);
        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public void deleteWarehouse(Long id){
        WareHouse wareHouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Warehouse with id " + id + " not found"));
        wareHouse.getInventory_collection().forEach(inv -> inv.getWarehouses().remove(wareHouse));
        inventoryRepository.saveAll(wareHouse.getInventory_collection());
        warehouseRepository.delete(wareHouse);
    }

    @Transactional
    public void deleteWarehouseInventory(Long warehouseId,Long inventoryId){
        WareHouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(()-> new DataNotFoundException("Warehouse with id" + warehouseId + "not found"));
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new DataNotFoundException("Inventory with id" + inventoryId + "not found"));
        warehouse.getInventory_collection().remove(inventory);
        inventory.getWarehouses().remove(warehouse);
        warehouseRepository.save(warehouse);
        inventoryRepository.save(inventory);
    }
}
