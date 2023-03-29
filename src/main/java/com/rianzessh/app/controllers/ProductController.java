package com.rianzessh.app.controllers;

import com.rianzessh.app.models.entities.Product;
import com.rianzessh.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/km")
    public String getData() {
        String uri = "https://api.kampusmerdeka.kemdikbud.go.id/magang/browse/position?offset=0&limit=20&sector_id=19ecc0f8-8c0a-45c8-a96b-a7ba8c97caf1&sort_by=published_time&order=desc";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }
    @PostMapping
    public Product createOne(@RequestBody Product product) {
        return productService.insert(product);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneData(@PathVariable("id") String id) {
        return productService.findOne(id);
    }

    @PutMapping
    public Product updateData(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void removeData(@PathVariable("id") String id) {
        productService.removeOne(id);
    }

}