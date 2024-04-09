package com.example.productservice.projections;

public interface ProductWithIdAndTitle {
    // this getter method should match with the alias name of the query in repository
    Long getId();
    String getTitle();
}
