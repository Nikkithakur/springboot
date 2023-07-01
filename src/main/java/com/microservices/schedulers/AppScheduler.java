package com.microservices.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppScheduler {

	
//	@Scheduled(cron = "")
	public void test1() {
		log.info("test1 scheduler invoked");
	}
}
