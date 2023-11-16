package com.example.productservice.Controller;

import com.example.productservice.Dtos.ProductRequest;
import com.example.productservice.Dtos.ProductResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//generates a random port here
class ProductControllerTest {
//    theres ntg to mock here
      @LocalServerPort
      private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init() {
        restTemplate=new RestTemplate();

    }
    @BeforeEach
   public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/product");
    }

    @Test
    void createProduct() {
        ProductRequest productRequest=ProductRequest.
                builder().
                name("dummy")
                .description("dummyDescription")
                .price(100)
                .build();
        ProductResponse response= restTemplate.postForObject(baseUrl,productRequest,ProductResponse.class);
        assertEquals("dummy",response.getName());
//        assertEquals(1,h2Repository.f);
    }

    @Test
    void getAllProducts() {
    }
}