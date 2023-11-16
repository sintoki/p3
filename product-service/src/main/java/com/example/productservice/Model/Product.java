package com.example.productservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id

private int id;

    private String name;
    private  String description;

    private  int price;

}
