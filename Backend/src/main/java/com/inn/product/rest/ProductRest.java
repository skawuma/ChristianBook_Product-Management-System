package com.inn.product.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inn.product.entity.Products;

@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public interface ProductRest {

    @GetMapping(path = "/searchProduct/{sku}")
    public ResponseEntity<?> searchProduct(@PathVariable String sku);
}
