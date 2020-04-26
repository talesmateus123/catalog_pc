/** 
 * This is the class "DBService". Which will be able to instantiate all data domain and send it to database through their respective repositories.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pml.domain.User;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService;
	public void instantiateTestDatabase() {
		User user = new User(null, "tales123",  "1234");
		userService.insert(user);
	}
	
	
}
