package com.microservices.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DownstreamService {

	@Async(value = "simpleAsyncExecutor")
	public void test(ResponseEntity<Map> responseEntity) {
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("{}", responseEntity);
		}

	}
}
