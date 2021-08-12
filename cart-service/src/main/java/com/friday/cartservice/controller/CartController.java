package com.friday.cartservice.controller;


import com.friday.cartservice.entity.Product;
import com.friday.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getCartProducts() {
        return new ResponseEntity<>(cartService.getCartProducts(), HttpStatus.OK);
    }

    @GetMapping("/deleteOne/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        cartService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/deleteAll")
    public ResponseEntity deleteProducts() {
        cartService.deleteAllProducts();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return new ResponseEntity<>("cart microservice", HttpStatus.OK);
    }
}
