package com.giridhari.preachingassistant.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DataSourceConfig {
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
    
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
