package com.turkcell.authserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @PostMapping
    public String Test(){
        return "eklendi";
    }

    @DeleteMapping
    public String TestDelete(){return "silindi";}

    @PutMapping
    public String put(){return "güncellendi";}
}
