package com.example.productservice.advices;

import com.example.productservice.dto.ExceptionDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setStatus("400");

        return  new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> InvalidProductId(InvalidProductIdException invalidProductIdException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid Product Id with given " + invalidProductIdException.getProductId());
        exceptionDto.setStatus("400");
        return  new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
    }
}
