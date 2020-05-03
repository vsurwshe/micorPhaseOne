package org.profile.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
				.groupName("ProfileAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.profile.controller"))
				.build()
				.apiInfo(apiInfo());
	}

	@Bean
	public Docket customerDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("CustomerAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.customer.controller"))
				.build()
				.apiInfo(apiInfo());
	}

	@Bean
	public Docket foodDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("FoodsAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.food.controller"))
				.build()
				.apiInfo(apiInfo());
	}
	
	@Bean
	public Docket hotelTabelDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("HotelTabelAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.hoteltabel.controller"))
				.build()
				.apiInfo(apiInfo());
	}
	@Bean
	public Docket paymentDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("PaymentAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.payment.controller"))
				.build()
				.apiInfo(apiInfo());
	}
	
	public Docket addressDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("AddressAPI")
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.address.controller"))
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
