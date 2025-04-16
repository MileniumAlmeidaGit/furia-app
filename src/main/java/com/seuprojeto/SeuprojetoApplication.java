package com.seuprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.seuprojeto.model")
public class SeuprojetoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeuprojetoApplication.class, args);
    }

}
