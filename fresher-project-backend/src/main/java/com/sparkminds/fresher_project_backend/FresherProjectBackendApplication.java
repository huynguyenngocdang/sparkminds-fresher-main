package com.sparkminds.fresher_project_backend;

import com.sparkminds.fresher_project_backend.service.DatabasePropertiesService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FresherProjectBackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(FresherProjectBackendApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(DatabasePropertiesService dbService) {
//		return args -> {
//			dbService.printDatabaseProperties();
//		};
//	}

}
