/** 
 * This is the class "TestConfig". Which will be able to serve to configure the database with all data test.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pml.services.DBService;
import com.pml.services.EmailService;
import com.pml.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	@Autowired
	private DBService dBService;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	// @Bean
	public boolean instantiateDatabase() {
		dBService.instantiateDefaultClients();
		return true;
	}
	
	
}
