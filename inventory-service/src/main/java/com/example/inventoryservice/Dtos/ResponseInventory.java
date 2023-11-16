package com.example.inventoryservice.Dtos;

import lombok.*;

@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder

public class ResponseInventory {

    public  String skuCodes;
    public boolean f1;
}
