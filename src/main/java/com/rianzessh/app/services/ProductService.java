package com.rianzessh.app.services;

import com.rianzessh.app.models.entities.Product;
import com.rianzessh.app.models.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product insert(Product product) {
        String uid = UUID.randomUUID().toString();
        product.setId(uid);
        return productRepo.save(product);
    }

    public Product update(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(String id) {
        return productRepo.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(String id) {
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }

}