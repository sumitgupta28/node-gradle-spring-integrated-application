package com.fsd.taskmanager;

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

/**
 * This Class generate the swagger file for the task manager services. 
 * @author Sumit
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Create Dockket Bean
	 * @return
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.fsd.taskmanager.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Task Manager API's").description("Task Manager API's")
				.contact(new Contact("ABC Name", "www.fsd-learn.net", "sumitgupta28@gmail.com")).version("1.0.0")
				.build();
	}
}
