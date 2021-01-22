package com.techmojo.trend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return regex("/.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Trends API")
				.description("Swagger API for Trends Test")
				.termsOfServiceUrl("Terms Of Service")
				.contact(new Contact("Mohit Agarwal","https://www.linkedin.com/in/mohit-agarwal-74b18a76/", "mohit6522@gmail.com"))
				.license("JavaInUse License")
				.licenseUrl("javainuse@gmail.com").version("1.0").build();
	}

}