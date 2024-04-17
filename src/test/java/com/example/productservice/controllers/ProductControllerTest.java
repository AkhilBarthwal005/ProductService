package com.example.productservice.controllers;

import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProductByIdValidCase() throws InvalidProductIdException {
        Product product = new Product();
        product.setId(10l);
        product.setPrice(102000);
        product.setTitle("Iphone 15");

        when(productService.getProductById(10l)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(10l);

        assertEquals(product ,response.getBody());

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testGetProductByIdInValidCase() throws InvalidProductIdException {
       when(productService.getProductById(1000l)).thenThrow(new InvalidProductIdException(1000l,"invalid id "));

       assertThrows(InvalidProductIdException.class , ()-> productController.getProductById(1000l));

    }

    @Test
    void getAllProducts() {
        List<Product> productsList = new ArrayList<>();
        Product product1 = new Product();
        product1.setTitle("SmartPhone");
        product1.setDescription("A new Smart Phone");
        product1.setPrice(50000);

        Product product2 = new Product();
        product2.setTitle("Laptop");
        product2.setDescription("A new Laptop");
        product2.setPrice(80000);

        productsList.add(product1);
        productsList.add(product2);

        when(productService.getAllProducts()).thenReturn(productsList);
        Object[] arr1 = productsList.toArray();
        Object[] arr2 = productService.getAllProducts().toArray();

        assertArrayEquals(arr1, arr2);
    }
}