package com.example.productservice.commons;

import com.example.productservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticateCommons {

    private RestTemplate restTemplate;

    @Autowired
    public AuthenticateCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String token){
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/users/validate/" + token, UserDTO.class);

        if(responseEntity.getBody() == null){
            // Invalid token
            return null;
        }
        return responseEntity.getBody();
    }
}
