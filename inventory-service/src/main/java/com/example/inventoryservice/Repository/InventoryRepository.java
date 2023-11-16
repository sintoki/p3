package com.example.inventoryservice.Repository;

import com.example.inventoryservice.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);

}
