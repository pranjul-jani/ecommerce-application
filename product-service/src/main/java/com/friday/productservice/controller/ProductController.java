package com.friday.productservice.controller;

import com.friday.productservice.entity.Product;
import com.friday.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product/")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addOne/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/addList")
    public ResponseEntity<List<Product>> addProductList(@RequestBody List<Product> productList) {
        return new ResponseEntity<>(productService.saveProductList(productList), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return  new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // send a message to product queue
    @GetMapping("/sendToCart/{id}")
    public ResponseEntity<Product> sendToCart(@PathVariable long id) {
        Optional<Product> product = productService.sendToCart(id);
        if (!product.isPresent())
            return new ResponseEntity<>(product.get(), HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

}
