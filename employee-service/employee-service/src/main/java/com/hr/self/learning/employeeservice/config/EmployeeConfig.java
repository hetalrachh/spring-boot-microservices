package com.hr.self.learning.employeeservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${address.service.baseUrl}") String baseUrl, RestTemplateBuilder builder) {
        return builder.rootUri(baseUrl).build();
    }

    @Bean
    public WebClient webClient(@Value("${address.service.baseUrl}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
