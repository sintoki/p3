package com.example.productservice.Service;

import com.example.productservice.Dtos.ProductRequest;
import com.example.productservice.Dtos.ProductResponse;
import com.example.productservice.Model.Product;
import com.example.productservice.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
//@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
//    wtd
        Product product= Product.builder().
                name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();


         product=  productRepository.save(product);
         return mapper(product);
    }
    public List<ProductResponse> getAllProducts() {
        List<Product> product=productRepository.findAll();
        List<ProductResponse>productResponses=  product.stream().map(this::mapper).collect(Collectors.toList());
        return productResponses;

//        u get the list of products now you need to changeg some simple stuff what is it
    }

    public  ProductResponse mapper( Product product){
        ProductResponse productResponse=ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
        return productResponse;
    }
}
