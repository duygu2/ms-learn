package com.example.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final WebClient.Builder webClientBuilder;
    @PostMapping
    public String addOrder(@RequestParam int productId){

        var result=webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8080/products?productId="+productId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        if(result<0){
            throw new RuntimeException("ürün stokta yok");
        }
        return "sipariş eklendi";
    }

}
