package com.example.productservice.services;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    SelfProductService(ProductRepository productRepository , CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Product convertProductDtoToProduct(ProductDto fakeStoreProductDto){
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
        // fetch the product from the db with given id
        Optional<Product> optionalProduct = productRepository.findById(id);
        return  optionalProduct.isEmpty()? null : optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void createProduct(Product product) {
        Category category = product.getCategory();
        // if we are using cascade type All then as soon as we save product it will save category as well.
//        if(category.getId() == null){
//            Category saveCategory = categoryRepository.save(category);
//            product.setCategory(saveCategory);
//        }

        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) throws InvalidProductIdException {
        Product product = convertProductDtoToProduct(productDto);
        Product savedProduct = getProductById(id);
        if(product.getTitle() != null){
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription() !=null){
            savedProduct.setDescription(product.getDescription());
        }

        productRepository.save(savedProduct);
        return savedProduct;

    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws InvalidProductIdException {
        Product productById = getProductById(id);
        productRepository.delete(productById);
    }
}
