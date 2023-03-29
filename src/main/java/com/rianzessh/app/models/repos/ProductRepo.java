package com.rianzessh.app.models.repos;

import com.rianzessh.app.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);

    Product findById(String id);

    void deleteById(String id);
}