package com.example.productservice.inheritancerepersention.jointable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED) // because we have multiple way to do this thing that why we use strategy design pattern
public abstract class User {
    @Id
    private long userId;
    private String name;
    private int age;
}
