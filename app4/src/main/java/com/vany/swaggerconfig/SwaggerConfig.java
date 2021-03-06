package com.vany.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.vany.controller"))
				.build()
				.apiInfo(apiInfo());
		
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		 return new ApiInfo("V & Y SOFT. TECH. PVT. LTD.", 
				   "This Rest API Development", 
				   "V0.1", 
				   "Vishva & Yogesh Visit http://vany.is-best.net/ ", 
				   "Apache License Version 2.0", 
				   "https://www.apache.org/licenses/LICENSE-2.0", 
				   "Contact us :- v2018y@gmail.com");
	}
}
