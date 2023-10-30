package com.santanderInternetBanking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger API", version = "1", description = "Swagger Desafio Santander"))
public class SantanderInternetBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderInternetBankingApplication.class, args);
	}

}
