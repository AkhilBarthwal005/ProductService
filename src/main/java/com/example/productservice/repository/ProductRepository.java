package com.example.productservice.repository;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long id);

//    @Query("select p from Product p where p.title like '%p%'")
    @Query("select p.id  as id , p.title as title from Product p where p.title like '%p%'")
    // it will give conversion issue because we are not returning complement product object , we are returning id and title only to solve this issue we create interface in projections package
//    List<Product> someRandomQuery();
    List<ProductWithIdAndTitle> someRandomQuery();


    // for sql query

    // it will make 2 DB calls
    //fist it will fetch the product then it will fetch will category
    @Query(value = "select  * from Product  p where p.id = 2 " , nativeQuery = true)
    List<Product> someRandomQueryWithSql();

    // it will fetch the category as well because product object contains category object and hibernate has to map this query with proudct object so he need to find category and make object product accordingly.



}
