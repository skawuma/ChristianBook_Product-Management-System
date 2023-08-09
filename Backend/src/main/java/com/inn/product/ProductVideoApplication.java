package com.inn.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;

@SpringBootApplication
@EnableAsync
public class ProductVideoApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) throws JAXBException {
        SpringApplication.run(ProductVideoApplication.class, args);
    }


}
