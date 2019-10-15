package com.daisuke.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ContentNegotiator implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	WebMvcConfigurer.super.configureContentNegotiation(configurer);
	configurer.defaultContentType(MediaType.APPLICATION_JSON).ignoreAcceptHeader(true).favorPathExtension(true)
		.favorParameter(false).useRegisteredExtensionsOnly(true).mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML).mediaType("csv", MediaType.TEXT_PLAIN);
    }

}
