package com.gravenium.temperature.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${app.version}")
	private String appVersion;

	@Bean
	public Docket apiDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.gravenium.temperature.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metaData());
		return docket;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Temperature REST API")
				.description("Simple REST API that gives you current temperature for given city name or city id.")
				.license("MIT")
				.licenseUrl("https://github.com/hkoskinen/temperature/blob/master/LICENSE.md")
				.termsOfServiceUrl("")
				.contact(new Contact("Henri Koskinen", "https://github.com/hkoskinen", "hkoskinen@protonmail.com"))
				.version(appVersion)
				.build();
	}
}
