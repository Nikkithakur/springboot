package com.microservices.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

	@Value("${custom.readtimeout:1}")
	private long readTimeout;

	@Value("${custom.connect-timeout:1}")
	private long connectionTimeout;

	@Primary
	@Bean("restTemplate")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean("timeoutRestTemplate")
	public RestTemplate timeoutRestTemplate() {
		return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(connectionTimeout))
				.setReadTimeout(Duration.ofSeconds(readTimeout)).build();
	}

	@Bean("objectMapper")
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper;
	}

	@Bean("simpleAsyncExecutor")
	public SimpleAsyncTaskExecutor executor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setThreadNamePrefix("AsyncExec-->");
		executor.setConcurrencyLimit(Runtime.getRuntime().availableProcessors() - 2);
		return executor;
	}

}
