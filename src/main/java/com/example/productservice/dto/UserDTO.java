package com.example.productservice.dto;

import com.example.productservice.models.Role;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    @ManyToOne
    private List<Role> roles;
    private boolean isEmailVerified;
}
