package com.example.productservice.controllers;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

//    @Autowired  it is optional now with new spring
    public ProductController(FakeStoreProductService productService) {
        this.productService = productService;
    }

    // localhost:8080/products/10
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){

        return  productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product){

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id , @RequestBody ProductDto productDto){
        return productService.updateProduct(id,productDto);
    }

    @PutMapping("/{id}")
    public  Product replaceProduct(@PathVariable("id") Long id , @RequestBody ProductDto productDto){
        return productService.replaceProduct(id,productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){

    }
}
