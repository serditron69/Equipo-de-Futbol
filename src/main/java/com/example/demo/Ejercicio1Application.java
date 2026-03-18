package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ejercicio1Application {

	public static void main(String[] args) {
		loadEnv();

		SpringApplication.run(Ejercicio1Application.class, args);
	}

	private static void loadEnv() {
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.ignoreIfMalformed()
				.load();

		// Corrige DB_USERNAME → DB_USER (como en tu .env)
		String dbUrl = dotenv.get("DB_URL");
		String dbUser = dotenv.get("DB_USER");
		String dbPassword = dotenv.get("DB_PASSWORD");

		// Solo setea si NO es null
		if (dbUrl != null) System.setProperty("DB_URL", dbUrl);
		if (dbUser != null) System.setProperty("DB_USERNAME", dbUser);  // ← Mapea DB_USER a DB_USERNAME si lo necesitas
		if (dbPassword != null) System.setProperty("DB_PASSWORD", dbPassword);
	}
}
