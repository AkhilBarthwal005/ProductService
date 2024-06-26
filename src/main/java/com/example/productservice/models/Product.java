package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String description;
    private String imageUrl;
}
