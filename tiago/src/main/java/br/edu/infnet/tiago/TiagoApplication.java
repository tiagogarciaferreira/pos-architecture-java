package br.edu.infnet.tiago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiagoApplication.class, args);
    }
}