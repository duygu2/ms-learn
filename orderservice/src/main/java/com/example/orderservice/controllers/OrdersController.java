package com.example.orderservice.controllers;

import com.example.orderservice.clients.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.turkcell.common.events.OrderCreatedEvent;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
   // private final WebClient.Builder webClientBuilder;

    private final ProductServiceClient productServiceClient;
    private final KafkaTemplate<String,Object> kafkaTemplate;
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
        //kafkaya mesaj gönder
        kafkaTemplate.send("orderTopic", "NewOrder", new OrderCreatedEvent(1, LocalDateTime.now().minusDays(3)));
        return "sipariş eklendi";

}
}
