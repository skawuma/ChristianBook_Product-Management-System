package com.inn.product.service;

import org.springframework.http.ResponseEntity;

import com.inn.product.entity.Products;

public interface ProductService {

    ResponseEntity<?> searchProduct(String sku);
}
