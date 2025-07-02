package com.parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsOrdersApplication {
// Este comando sirve para encender el backend:    ./mvnw spring-boot:run  <--ponerlo en la terminal
	public static void main(String[] args) {
		SpringApplication.run(MsOrdersApplication.class, args);
	}

}
