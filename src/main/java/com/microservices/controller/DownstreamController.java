package com.microservices.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DownstreamController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("timeoutRestTemplate")
	private RestTemplate timeoutRestTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private DownstreamService downstreamService;

	@GetMapping("/")
	public Map testDownstreamConnection() {
		HttpEntity entity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<Map> responseEntity = restTemplate.exchange("https://reqres.in/api/users?page=2", HttpMethod.GET,
				entity, Map.class);
		downstreamService.test(responseEntity);
		return responseEntity.getBody();
	}
	
	@GetMapping("/post")
	public Map postDownstreamConnection() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		Object requestBody = mapper.readValue("{\"name\": \"deloitte\",\"job\": \"leader\"}".getBytes(), Object.class);
		HttpEntity entity = new HttpEntity<>(requestBody, httpHeaders);
		ResponseEntity<Map> responseEntity = restTemplate.exchange("https://reqres.in/api/users", HttpMethod.POST,
				entity, Map.class);
		log.info("{}", responseEntity);
		return responseEntity.getBody();
	}
	
	@GetMapping("/timeout")
	public void testTimeout() {
		ResponseEntity<Map> responseEntity = timeoutRestTemplate.exchange("https://reqres.in/api/users?delay=10",  HttpMethod.GET,
				null, Map.class);
		log.info("{}",responseEntity.getBody());
	}
	
	@GetMapping("/resources")
	public void loadResourceFiles() throws Exception{
		ClassPathResource r = new ClassPathResource("test.json");
		Map readValue = mapper.readValue(r.getFile(), Map.class);
		log.info("{}", readValue);
	}
}
