package com.example.productservice.services;

import com.example.productservice.dto.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    private String baseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        int randomNumber = (int) (Math.random() * 100) + 1;
        category.setId(randomNumber);
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        // call the fake store api with given id here...
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(baseUrl +"/" + id, FakeStoreProductDto.class);// url , expected output class

        // convert fake store dto to actual Product object

        return fakeStoreProductDto == null ? null : convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList = Arrays.asList(restTemplate.getForObject(baseUrl, FakeStoreProductDto[].class));
        if(fakeStoreProductDtoList.size() != 0){
            return fakeStoreProductDtoList.stream().map(this::convertFakeStoreProductDtoToProduct).toList();
        }

        return null;
    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
