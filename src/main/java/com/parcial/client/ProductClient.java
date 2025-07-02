package com.parcial.client;

import com.parcial.dto.ProductDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackProduct")
    public ProductDTO getProductById(Long productId) {
        return restTemplate.getForObject(
                "http://localhost:8081/api/products/{id}",
                ProductDTO.class,
                productId
        );
    }

    public ProductDTO fallbackProduct(Long productId, Throwable throwable) {
        ProductDTO fallback = new ProductDTO();
        fallback.setId(productId);
        fallback.setName("Producto no disponible");
        fallback.setPrice(0.0);
        fallback.setStock(0);
        fallback.setCategory("N/A");
        return fallback;
    }
}

