package com.inn.product.entity;

import java.util.Set;

public class Products {

    private Set<String> authos;

    private String titla;

    private String price;

    public Set<String> getAuthos() {
        return authos;
    }

    public void setAuthos(Set<String> authos) {
        this.authos = authos;
    }

    public String getTitla() {
        return titla;
    }

    public void setTitla(String titla) {
        this.titla = titla;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "authos=" + authos +
                ", titla='" + titla + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
