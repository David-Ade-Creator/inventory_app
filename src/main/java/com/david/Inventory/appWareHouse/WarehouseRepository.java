package com.david.Inventory.appWareHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<WareHouse,Long> {
    WareHouse findByRegistrationId(String regId);
}
