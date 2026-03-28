package com.casualthoughts.crud_app_with_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

@SpringBootApplication
public class CrudAppWithSecurityApplication {

	public static void main(String[] args) {
        // Load .env variables into System properties so Spring can see them
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(CrudAppWithSecurityApplication.class, args);
	}

}
