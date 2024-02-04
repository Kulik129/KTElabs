package ru.ktelabs.producingwebservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Prod {

    public static void main(String[] args) {
        SpringApplication.run(Prod.class, args);
    }

}

