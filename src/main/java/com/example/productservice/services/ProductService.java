package com.example.productservice.services;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    public Product getProductById( Long id) throws InvalidProductIdException;
    public Page<Product> getAllProducts(int pageNumber, int pageSize);

    public List<Product> getAllProducts();
    public void createProduct( Product product);
    public Product updateProduct( Long id , ProductDto productDto) throws InvalidProductIdException;
    public  Product replaceProduct( Long id , ProductDto productDto);
    public void deleteProduct(Long id) throws InvalidProductIdException;
}
