package com.inn.product.data;

import java.util.HashMap;
import java.util.Map;

import com.inn.product.entity.Products;

public class Data {

    public static Map<String, String> urlMap;

    public static Map<String, Products> productsMap = new HashMap<>();

    public static Map<String, String> getUrlMap() {
        return urlMap;
    }

    public static void setUrlMap(Map<String, String> urlMap) {
        Data.urlMap = urlMap;
    }

    public static Map<String, Products> getProductsMap() {
        return productsMap;
    }

    public static void setProductsMap(Map<String, Products> productsMap) {
        Data.productsMap = productsMap;
    }
}
