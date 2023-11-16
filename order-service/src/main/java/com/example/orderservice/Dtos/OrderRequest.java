package com.example.orderservice.Dtos;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsList;

}
