package com.pml.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.pml.security.JWTAuthenticationFilter;
import com.pml.security.JWTAuthorizationFilter;
import com.pml.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private Environment env;
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	/*
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/api/sectors/**",
			"/api/computers/**",
			"/api/computer_users/**",
			"/api/monitors/**",
			"/api/processors/**",
			"/api/ram_memories/**",
			"/api/storage_devices/**",
			"/api/printers/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/api/users/**",
			"/auth/forgot_password"
	};
	*/
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/api/sectors/**",
			"/api/computers/**",
			"/api/computer_users/**",
			"/api/monitors/**",
			"/api/processors/**",
			"/api/ram_memories/**",
			"/api/storage_devices/**",
			"/api/printers/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/api/sectors/**",
			"/api/computers/**",
			"/api/computer_users/**",
			"/api/monitors/**",
			"/api/processors/**",
			"/api/ram_memories/**",
			"/api/storage_devices/**",
			"/api/printers/**",
			"/api/users/**",
			"/auth/forgot_password"
	};	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		// Call the cors configuration below 
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		// Just permits POST method to users
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		// Just permits GET method publicly
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated();
		// Adding an Authentication Filter
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		// Adding an Authorization Filter
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	// Defines who the UserDetails is and what the encryption algorithm is
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	// Unlock requests from multiple sources (cross-origin)
	@Bean
	CorsConfigurationSource corsConfigutationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
