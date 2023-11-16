package com.example.orderservice.Service;

import com.example.orderservice.Dtos.OrderLineItemsDto;
import com.example.orderservice.Dtos.OrderRequest;
import com.example.orderservice.Dtos.ResponseInventory;
import com.example.orderservice.Models.Order;
import com.example.orderservice.Models.OrderLineItems;
import com.example.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList()
                .stream()
                .map(this::mapToDto)
                .toList();


   Order order=  Order.builder()
                        .orderNumber(UUID.randomUUID().toString())
                                .orderLineItemsList(orderLineItems)
                                        .build();

//           before saving the stuff need to check
//        pass the skucodes as list check once for all
        List<String>skuCodes=order.getOrderLineItemsList().stream()
                                           .map(OrderLineItems::getSkuCode).toList();


        ResponseInventory[] inventoryResponsArray = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(ResponseInventory[].class)
                .block();

        boolean allProductsisF1 = Arrays.stream(inventoryResponsArray)
                .allMatch(response -> response.isF1());
//        what actually shit is happening here
//        orderRepository.save(order);
        if (allProductsisF1) {
            orderRepository.save(order);
        }
           else {
                throw new IllegalArgumentException("qwertyuiopl,mnbvcxzaq");
            }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}