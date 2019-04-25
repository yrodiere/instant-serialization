package com.example.demo;

import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoCommandLineRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoCommandLineRunner.class);

	@Autowired
	private DemoEntityRepository demoEntityRepository;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("System timezone: {}", ZoneId.systemDefault());

		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setInstant(Instant.parse("2018-10-28T01:00:00Z"));
		LOGGER.info("Instant before save: {}", demoEntity.getInstant());
		demoEntityRepository.save(demoEntity);

		demoEntity = demoEntityRepository.findById(demoEntity.getId()).get();
		LOGGER.info("Instant after find: {}", demoEntity.getInstant());
	}
}
