package com.example.productservice.controllers;

import com.example.productservice.dto.ExceptionDto;
import com.example.productservice.dto.ProductDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.exceptions.ProductControllerException;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

//    @Autowired  it is optional now with new spring
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    // localhost:8080/products/10
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {

        return  productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id , @RequestBody ProductDto productDto) throws InvalidProductIdException {
        return productService.updateProduct(id,productDto);
    }

    @PutMapping("/{id}")
    public  Product replaceProduct(@PathVariable("id") Long id , @RequestBody ProductDto productDto){
        return productService.replaceProduct(id,productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws InvalidProductIdException {
        productService.deleteProduct(id);
    }

    // Exception Handling Specify to the product controller
    @ExceptionHandler(ProductControllerException.class)
    public ResponseEntity<Void> specifyToProductController(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
