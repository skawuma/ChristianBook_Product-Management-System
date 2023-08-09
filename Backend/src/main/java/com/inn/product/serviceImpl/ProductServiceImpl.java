package com.inn.product.serviceImpl;

import com.inn.product.data.Data;
import com.inn.product.data.ProcessData;
import com.inn.product.data.ProcessEachUrlData;
import com.inn.product.entity.Products;
import com.inn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ResponseEntity<?> searchProduct(String sku) {
        try {
            Map<String, String> map = Data.getUrlMap();
            if (map == null)
                return new ResponseEntity<>("{\"message\":\"Data is being processed. Try after sometime....!\"}", HttpStatus.OK);
            if (Data.getProductsMap() != null) {
                Map<String, Products> productsMap = Data.getProductsMap();
                Products products = productsMap.get(sku);
                if (!Objects.isNull(products)) {
//                    System.out.println("===========================================================");
                    return new ResponseEntity<Products>(products, HttpStatus.OK);
                }
            }
            ProcessData pro = new ProcessData(new RestTemplate());
            String data = map.get(sku);
            if (data == null || data.isEmpty())
                return new ResponseEntity<>("{\"message\":\"Invalid sku. Try with valid sku id....!\"}", HttpStatus.BAD_REQUEST);
            Products products = pro.getData(data);
            Data.getProductsMap().put(sku, products);
            return new ResponseEntity<Products>(products, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
