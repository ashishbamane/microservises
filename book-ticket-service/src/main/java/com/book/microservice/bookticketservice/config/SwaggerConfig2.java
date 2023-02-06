package com.book.microservice.bookticketservice.config;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Optional;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig2 implements WebMvcConfigurer {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.companyname.app"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("NAME OF SERVICE")
                .description("API Endpoint Decoration")
                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
	
	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
	 * registry.addRedirectViewController("/api/swagger-resources/configuration/ui",
	 * "/swagger-resources/configuration/ui"); registry.addRedirectViewController(
	 * "/api/swagger-resources/configuration/security",
	 * "/swagger-resources/configuration/security");
	 * registry.addRedirectViewController("/api/swagger-resources",
	 * "/swagger-resources"); }
	 */

	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations(
	 * "classpath:/META-INF/resources/swagger-ui.html");
	 * registry.addResourceHandler("/api/webjars/**").addResourceLocations(
	 * "classpath:/META-INF/resources/webjars/"); }
	 */
	
	/*@Bean public Docket docket() {
		  return new Docket(DocumentationType.SWAGGER_2).select().build();
		  return new Docket(DocumentationType.SWAGGER_2)
	  .enable(true) .apiInfo(new ApiInfoBuilder() .title("Swagger Super")
	  .description("Swagger Description details") .version("1.0").build())
	  .select() .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
	  .paths(PathSelectors.any()).build(); 
	 
	  }*/
	  
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build() .pathMapping("/"); }
	 */
	  
	 
}


