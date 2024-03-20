package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    public Product getProductById( Long id);
    public List<Product> getAllProducts();
    public void createProduct( Product product);
    public Product updateProduct( Long id ,Product product);
    public  Product replaceProduct( Long id , Product product);
    public void deleteProduct(Long id);
}
