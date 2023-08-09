package com.inn.product.restImpl;

import com.inn.product.entity.Products;
import com.inn.product.rest.ProductRest;
import com.inn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<?> searchProduct(String sku) {
        try{
            return productService.searchProduct(sku);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
