package com.rianzessh.app.controllers;

import com.rianzessh.app.dto.ResponseData;
import com.rianzessh.app.models.entities.Product;
import com.rianzessh.app.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
    public ResponseEntity<ResponseData<Product>> createOne(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError errs : errors.getAllErrors()) {
                responseData.getMessages().add(errs.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(productService.insert(product));
        return ResponseEntity.ok(responseData);
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
    public ResponseEntity<ResponseData<Product>> updateData(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError errs : errors.getAllErrors()) {
                responseData.getMessages().add(errs.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(productService.update(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeData(@PathVariable("id") String id) {
        productService.removeOne(id);
    }

}