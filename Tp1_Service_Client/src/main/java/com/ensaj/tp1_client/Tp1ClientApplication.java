package com.ensaj.tp1_client;

import com.ensaj.tp1_client.Models.Client;
import com.ensaj.tp1_client.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Tp1ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp1ClientApplication.class, args);
    }

}
