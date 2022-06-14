package com.david.Inventory.repository;

import com.david.Inventory.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WareHouse,Long> {
    WareHouse findByRegistrationId(String regId);
}
