//package com.example.productservice;
//
//import com.example.productservice.controllers.ProductController;
//import com.example.productservice.exceptions.InvalidProductIdException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//
//@SpringBootTest
//
//public class SampleTest {
//    // 3 A's
//    /*
//    Arrange -> prepare data
//     Act -> call the required function
//      Assert -> check the expected output with actual output.
//     */
//
//    @Autowired
//    private ProductController productController;
//
//    @Test
//    void testOnePlusOneIsTwo(){
//        // we can have as many as assert condition and test will pass only and only if all the assert condition pass successfully.
//        int i = 1 + 1; // arrange + act
//        int x = 5 + 5;
////
////        assert i == 2;
////
////        assert x == 10;
//
//        assertEquals(2,i); // ideal order of parameters expected , actual , message
//
//        assertThrows(InvalidProductIdException.class,
//                ()-> productController.getProductById(100l));
//
//
//    }
//}
