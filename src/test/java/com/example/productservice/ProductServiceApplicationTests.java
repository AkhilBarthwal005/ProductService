package com.example.productservice;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductWithIdAndTitle;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public  void testQuery(){
        List<ProductWithIdAndTitle> products = productRepository.someRandomQuery();
        for (ProductWithIdAndTitle p:
             products) {
            System.out.println(p.getId() + " " + p.getTitle());
        }
        System.out.println("Debug");
    }

    @Test
    public  void testQuerySQL(){
        List<Product> products = productRepository.someRandomQueryWithSql();
        for (Product p:
                products) {
            System.out.println(p.getId() + " " + p.getTitle());
        }
        System.out.println("Debug");
    }

    @Test
    public  void testQueryDerivedQuery(){
        List<Product> products = productRepository.findAllById(Collections.singleton(2l)); // it make join call for product and category because it is inbuilt query of hibernate
        for (Product p:
                products) {
            System.out.println(p.getId() + " " + p.getTitle());
        }
        System.out.println("Debug");
    }

    @Test
    public void DeleteCategory(){
        categoryRepository.deleteById(52l);
    }

}
