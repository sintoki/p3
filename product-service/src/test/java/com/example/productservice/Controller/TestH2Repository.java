package com.example.productservice.Controller;

import com.example.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product,Integer> {
}
