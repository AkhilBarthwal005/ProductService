package com.example.productservice.services;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    private String baseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(ProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        long randomNumber = (long) (Math.random() * 100) + 1;
        category.setId(randomNumber);
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        // call the fake store api with given id here...
        ProductDto fakeStoreProductDto = restTemplate.getForObject(baseUrl +"/" + id, ProductDto.class);// url , expected output class

        // convert fake store dto to actual Product object
        if(fakeStoreProductDto == null){
            throw new InvalidProductIdException( id ,"Invalid Product Id");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductDto> fakeStoreProductDtoList = Arrays.asList(restTemplate.getForObject(baseUrl, ProductDto[].class));
        if(fakeStoreProductDtoList.size() != 0){
            return fakeStoreProductDtoList.stream().map(this::convertFakeStoreProductDtoToProduct).toList();
        }

        return null;
    }

    @Override
    public void createProduct(Product product) {}

    @Override
    public Product updateProduct(Long id,  ProductDto productDto) {
        HttpEntity<ProductDto> requestEntity = new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.PATCH, requestEntity, ProductDto.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return convertFakeStoreProductDtoToProduct(responseEntity.getBody());
        }
        return  null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, ProductDto.class);
        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor(ProductDto.class, restTemplate.getMessageConverters());
        ProductDto fakeStoreProductDto = restTemplate.execute(baseUrl + "/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
