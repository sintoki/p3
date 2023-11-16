package com.example.productservice.Dtos;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private  String description;

    private  int price;
}
