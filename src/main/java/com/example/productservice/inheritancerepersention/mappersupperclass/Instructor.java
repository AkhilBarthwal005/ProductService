package com.example.productservice.inheritancerepersention.mappersupperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_instructor") // to change the name of class
public class Instructor extends User{
    private String specialization;
}
