/** 
 * This is the class "TestConfig". Which will be able to iserve to configure the database with all data test.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pml.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dBService;
	@Bean
	public boolean instantiateDatabase() {
		
		return true;
	}

}
