package com.giridhari.preachingassistant;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.giridhari.preachingassistant.model.Devotee;
import com.giridhari.preachingassistant.repo.DevoteeRepo;

@SpringBootApplication
public class PreachingAssistantApplication {
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(PreachingAssistantApplication.class, args);
	}
    
    @Bean
    public DataSource dataSource() {
    	DataSource ds = new DataSource();
    	ds.setDriverClassName(driverClassName);
    	ds.setUrl(url);
    	ds.setUsername(username);
    	ds.setPassword(password);
    	
    	return ds;
    }
}
