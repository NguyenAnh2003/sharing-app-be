package com.example.socialapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(description = "API DOC for social network",title = "API DOCS social network"),
		servers = {@Server(description = "Local env",url = "http://localhost:8080")}
)

@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer",
				type = SecuritySchemeType.HTTP,
				bearerFormat = "JWT",
				in = SecuritySchemeIn.HEADER)

@SpringBootApplication
public class SocialApiApplication {
	/* rename to sharing app
	* with basic sharing post and following user
	* not including chat
	* not a complex system */
	public static void main(String[] args) {
		SpringApplication.run(SocialApiApplication.class, args);
	}
}
