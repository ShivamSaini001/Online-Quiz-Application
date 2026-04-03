package com.quiz.initializer;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
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
	UserDetailsManager userDetailsManager;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static String DEFAULT_ADMIN_USERNAME = "admin@example.com";
	private static String DEFAULT_ADMIN_PASSWORD = "admin123";

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
			
			userDetailsManager.loadUserByUsername(DEFAULT_ADMIN_USERNAME);

			System.out.println("Database initialized successfully...");
		} catch (UsernameNotFoundException e) {
			UserDetails user = User.withUsername(DEFAULT_ADMIN_USERNAME)
					.password(passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD))
					.roles("ADMIN").build();

			userDetailsManager.createUser(user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize database..." + e.getMessage());
		}
	}

}
