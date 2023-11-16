package com.example.inventoryservice.Service;

import com.example.inventoryservice.Dtos.ResponseInventory;
import com.example.inventoryservice.Model.Inventory;
import com.example.inventoryservice.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Transactional
//    just mapped the stuff where it belongs ep
    public List<ResponseInventory> isInStock(List<String> skuCode) {
   List <Inventory>inventory= inventoryRepository.findBySkuCodeIn(skuCode);
    return inventory.stream().map(inventory1 -> responseMapper(inventory1)).collect(Collectors.toList());


    }

    private ResponseInventory responseMapper(Inventory inventory1) {
       return ResponseInventory.builder()
                .skuCodes(inventory1.getSkuCode())
                .f1(inventory1.getQuantity()>1)
                .build();

    }
}
