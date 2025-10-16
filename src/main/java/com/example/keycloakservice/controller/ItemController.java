package com.example.keycloakservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @GetMapping
    public List<String> getItems(){
        List<String> items = new ArrayList<>();
        items.add("item1");
        items.add("item2");
        return  items;
    }
}
