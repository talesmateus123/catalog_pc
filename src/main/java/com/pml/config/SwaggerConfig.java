package com.pml.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ResponseMessage m200 = simpleMessage(200, "Ok");
	private final ResponseMessage m201 = simpleMessage(201, "Created");
	private final ResponseMessage m204put = simpleMessage(204, "No content");
	private final ResponseMessage m204del = simpleMessage(204, "Ok");
	private final ResponseMessage m401 = simpleMessage(401, "Unauthorized");
	private final ResponseMessage m403 = simpleMessage(403, "Forbidden");
	private final ResponseMessage m404 = simpleMessage(404, "Not Found");
	private final ResponseMessage m422 = simpleMessage(422, "Validation error");	
	private final ResponseMessage m500 = simpleMessage(500, "Unexpected error");
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m401, m403, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m401, m403, m422, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m401, m403, m404, m422, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m401, m403, m404, m500))
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("com.pml.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Restfull API for computer catalogation",
				"This API is used for the management of technology equipments from \"Prefeitura Municipal de Ladário\"", "Version 1.0",
				"",
				new Contact("Tales Mateus", "", "talesmateus1999@hotmail.com"),
				"", "", Collections.emptyList()
		);
	}
	
	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
	


}