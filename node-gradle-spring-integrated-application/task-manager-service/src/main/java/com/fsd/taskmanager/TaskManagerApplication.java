package com.fsd.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
/**
 * TaskManager Startup Application 
 * @author Sumit
 *
 */
@SpringBootApplication
public class TaskManagerApplication {
	/**
	 * Main Start Method
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
	/**
	 * Creating bean for logging request data
	 * @return
	 */
	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
	
	

}
