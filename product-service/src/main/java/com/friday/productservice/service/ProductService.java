package com.friday.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friday.productservice.entity.Product;
import com.friday.productservice.exception.ProductNotFoundException;
import com.friday.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${product.jms.destination}")
    private String jmsQueue;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveProductList(List<Product> productList) {
        return productRepository.saveAll(productList);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //send a product to message queue
    public Optional<Product> sendToCart(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent())
            return product;

        try {
            ObjectMapper mapper = new ObjectMapper();

            //Convert the object to string
            String jsonInString = mapper.writeValueAsString(product.get());

            //send the data to the message queue
            jmsTemplate.convertAndSend(jmsQueue, jsonInString);

            return product;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ProductNotFoundException(product.get().getName() + " product not found");
        }
    }
}
