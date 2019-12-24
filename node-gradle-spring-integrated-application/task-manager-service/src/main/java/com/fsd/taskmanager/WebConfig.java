package com.fsd.taskmanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Web configuration to add allowed CORS mapping. 
 * @author Sumit
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * adding CORS mapping. 
	 */
	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods(HttpMethod.DELETE.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
						HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name(), HttpMethod.POST.name())
				.allowedOrigins("*").allowedHeaders("*");
	}

}