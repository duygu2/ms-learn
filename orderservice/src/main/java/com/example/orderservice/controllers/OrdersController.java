package com.example.orderservice.controllers;

import com.example.orderservice.clients.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
   // private final WebClient.Builder webClientBuilder;

    private final ProductServiceClient productServiceClient;
    @PostMapping
    public String addOrder(@RequestParam int productId){

       /* var result=webClientBuilder
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
    */
        int stockResult= productServiceClient.getStockByProductId(productId);

        System.out.println("Ürün servisinden gelen cevap: " + stockResult);


        if(stockResult<0){
            throw new RuntimeException("ürün stokta yok");
        }
        return "sipariş eklendi";

}
}
