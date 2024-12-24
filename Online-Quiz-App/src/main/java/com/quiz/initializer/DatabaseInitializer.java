package com.quiz.initializer;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class DatabaseInitializer {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private ResourceLoader resourceLoader;

	@PostConstruct
	public void initializeDatabase() {

		try {
			Resource resource = resourceLoader.getResource("classpath:" + "sql/schema.sql");

			String sqlStatements = new String(Files.readAllBytes(Paths.get(resource.getURI())));

			String allQuries[] = sqlStatements.split(";");

			for (int index = 0; index < allQuries.length - 1; index++) {
				String query = allQuries[index];
				query = query.trim() + ";";
				jdbcTemplate.execute(query);
			}

			System.out.println("Database initialized successfully...");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize database..." + e.getMessage());
		}
	}

}
