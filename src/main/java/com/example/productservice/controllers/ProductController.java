package com.example.productservice.controllers;

import com.example.productservice.commons.AuthenticateCommons;
import com.example.productservice.dto.ExceptionDto;
import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.UserDTO;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.exceptions.ProductControllerException;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final AuthenticateCommons authenticateCommons;

    private final RestTemplate restTemplate;

//    @Autowired  it is optional now with new spring
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticateCommons authenticateCommons, RestTemplate restTemplate) {
        this.productService = productService;
        this.authenticateCommons = authenticateCommons;
        this.restTemplate = restTemplate;
    }

    // localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {



        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://UserService/users/10", String.class);

        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("token") String token){

        // check the token
        UserDTO userDTO = authenticateCommons.validateToken(token);
        if(userDTO == null){
            // throw exception
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/all/pages")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){

        Page<Product> products = productService.getAllProducts(pageNumber,pageSize);

        return new ResponseEntity<>(products,HttpStatus.OK);
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
