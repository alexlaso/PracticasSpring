package com.example.pokedex.pruebas.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("api-web").packagesToScan("com.example.pokedex.pruebas.controllers")
				.build();
	}

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info().title("Potato")
						.description("Potato"))
				.components(new Components().addSecuritySchemes("BasicAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.addSecurityItem(new SecurityRequirement().addList("BasicAuth"));
	}
}