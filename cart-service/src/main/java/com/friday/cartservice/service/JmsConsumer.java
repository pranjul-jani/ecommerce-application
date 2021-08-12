package com.friday.cartservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friday.cartservice.entity.Product;
import com.friday.cartservice.exception.ProductNotFoundException;
import com.friday.cartservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @Autowired
    ProductRepository productRepository;

    @JmsListener(destination = "${product.jms.destination}")
    public void consumeMessage(String data) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            //Json data to Product object
            Product product = mapper.readValue(data, Product.class);

            productRepository.save(product);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ProductNotFoundException("product was not found");
        }
    }
}
